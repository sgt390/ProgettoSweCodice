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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <BlockCalendar> convertToJSON(t: BlockCalendar): JSONObject {
        val blockCalendar = t as com.megalexa.models.blocks.BlockCalendar
        val allBlock = JSONObject()
        allBlock.put("blockType", "Calendar")
        val config = JSONObject()
        config.put("credentials", "getCredentials()")
        config.put("token", "getToken()")
        allBlock.put("config", config)
        return allBlock
    }

    public fun getToken(): Credential {
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
}
