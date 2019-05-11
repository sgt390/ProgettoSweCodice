package com.megalexa.util.service

import android.util.Log
import com.google.android.gms.auth.GoogleAuthUtil.getToken
import com.google.api.client.auth.oauth2.Credential
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.client.util.store.FileDataStoreFactory
import com.google.api.services.calendar.CalendarScopes
import com.megalexa.models.blocks.BlockCalendar
import com.megalexa.util.CalendarQuickstart
import org.json.JSONArray
import org.json.JSONObject
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStreamReader

object BlockCalendarService: BlockService() {
    val HTTP_TRANSPORT =  NetHttpTransport()
    private val TOKENS_DIRECTORY_PATH = "tokens"
    private val JSON_FACTORY = JacksonFactory.getDefaultInstance()
    private val APPLICATION_NAME = "Google Calendar API Java Quickstart"
    private val SCOPES = listOf(CalendarScopes.CALENDAR)
    private val CREDENTIALS_FILE_PATH = "/credentials.json"

    override fun convertFromJSON(jsonObject: JSONObject): BlockCalendar {
        return BlockCalendar()
    }

    override fun <BlockCalendar> convertToJSON(t: BlockCalendar): JSONObject {
        val allBlock = JSONObject()
        allBlock.put("blockType", "Calendar")
        val config = JSONObject()
        val credential = JSONObject()

        credential.put("installed", createInstalled(t))
        config.put("credential", credential)
        config.put("token", createToken(t))
        allBlock.put("config", config)
        return allBlock
    }

    fun getToken(): Credential {
        val HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport()
        val cred = CalendarQuickstart.getCredentials(HTTP_TRANSPORT)
        return cred
    }

//    private fun getCredentials(): JSONObject {
//        val cred : JSONObject = JSONObject()
//        cred.put("a","a")
//        return cred
//    }


    @Throws(IOException::class)
    public fun getCredentials(HTTP_TRANSPORT: NetHttpTransport): Credential {
        // Load client secrets.
        val `in` = this::class.java!!.getResourceAsStream(CREDENTIALS_FILE_PATH)
            ?: throw FileNotFoundException("Resource not found: $CREDENTIALS_FILE_PATH")
        val clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, InputStreamReader(`in`))
        Log.d("ContieneCredentials",clientSecrets.details.clientId)
        val flow = GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
            //.setDataStoreFactory(FileDataStoreFactory(File(TOKENS_DIRECTORY_PATH)))
            .setAccessType("offline").build()
        val receiver : LocalServerReceiver = (LocalServerReceiver.Builder()).build()
        Log.d("arrivedHere","sonoqua")
        val token = AuthorizationCodeInstalledApp(flow, receiver).authorize("user")
        Log.d("arrivedHere",token.accessToken)
        println("access_token= " + token.accessToken)
        println("expires in = " + token.expiresInSeconds!!)
        println("refresh_token= " + token.refreshToken)
        println("serverEncoded= " + token.tokenServerEncodedUrl)
        return token
    }

    private fun <BlockCalendar> createInstalled(t: BlockCalendar): JSONObject{
        val installed = JSONObject()
        val blockCalendar = t as com.megalexa.models.blocks.BlockCalendar
        installed.put("auth_provider_x509_cert_url", blockCalendar.getAuthProvider())
        installed.put("auth_uri", blockCalendar.getAuthUri())
        installed.put("client_id", "DalSecret")
        installed.put("client_secret", "DalSecret")
        installed.put("project_id", blockCalendar.getProjectId())
        val redUris = JSONArray()
        redUris.put(blockCalendar.getRedirect1())
        redUris.put(blockCalendar.getRedirect2())
        installed.put("redirect_uris", redUris)
        installed.put("token_uri", blockCalendar.getTokenUri())
        return installed
    }
    private fun <BlockCalendar> createToken(t: BlockCalendar): JSONObject{
        val blockCalendar = t as com.megalexa.models.blocks.BlockCalendar
        val token = JSONObject()
        token.put("access_token", "tokenDaSecret")
        token.put("expires_in", blockCalendar.getDate())
        token.put("scope", blockCalendar.getScope())
        token.put("token_type",blockCalendar.getTokenType())
        return token
    }
}
