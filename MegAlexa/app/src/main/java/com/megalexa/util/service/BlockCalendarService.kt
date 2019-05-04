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

    public fun getToken(credential: Credential): JSONObject {

        return JSONObject()
    }

//    private fun getCredentials(): JSONObject {
//        val cred : JSONObject = JSONObject()
//        cred.put("a","a")
//        return cred
//    }


    @Throws(IOException::class)
    public fun getCredentials(HTTP_TRANSPORT: NetHttpTransport): Credential {
        // Load client secrets.
        Log.d("Parma 1","Entrato qui")
        val `in` = this::class.java!!.getResourceAsStream(CREDENTIALS_FILE_PATH)
            ?: throw FileNotFoundException("Resource not found: $CREDENTIALS_FILE_PATH")
        Log.d("Parma 2","Passato di qua")
        val clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, InputStreamReader(`in`))
        // {"web":{"client_id":"1048781954504-4et458uubfu4chmgonlus6bv9c5ale1l.apps.googleusercontent.com","project_id":"megalexa","auth_uri":"https://accounts.google.com/o/oauth2/auth","token_uri":"https://oauth2.googleapis.com/token","auth_provider_x509_cert_url":"https://www.googleapis.com/oauth2/v1/certs","client_secret":"bkYtBK9CQjbkfJ7tUcc0PgjM"}}
        // Build flow and trigger user authorization request.
        Log.d("Parma 3",clientSecrets.details.tokenUri)
        val flow = GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
            //.setDataStoreFactory(FileDataStoreFactory(File(TOKENS_DIRECTORY_PATH)))
            .setAccessType("offline").build()
        Log.d("Parma 4","4")
        println("access_type: " + flow.accessType)
        println("client: " + flow.clientAuthentication)


        val receiver : LocalServerReceiver = (LocalServerReceiver.Builder()).build()
        Log.d("Parma 5",receiver.toString())
        val token = AuthorizationCodeInstalledApp(flow, receiver).authorize("user")
        Log.d("Parma 6",token.accessToken)
        println("access_token= " + token.accessToken)
        println("expires in = " + token.expiresInSeconds!!)
        println("refresh_token= " + token.refreshToken)
        println("serverEncoded= " + token.tokenServerEncodedUrl)
        return token
    }
}

/*
"blockType": "Calendar",
"config": {
    "credentials": {
        "installed": {
        "auth_provider_x509_cert_url": "https://www.googleapis.com/oauth2/v1/certs",
        "auth_uri": "https://accounts.google.com/o/oauth2/auth",
        "client_id": "974570768081-p3e8rfbsmd25ae4r445edsv3niugh72u.apps.googleusercontent.com",
        "client_secret": "MA-aItkDN3-dEQovlvtq3Mph",
        "project_id": "megalexa-1556132707047",
        "redirect_uris": [
        "urn:ietf:wg:oauth:2.0:oob",
        "http://localhost"
        ],
        "token_uri": "https://oauth2.googleapis.com/token"
    }
    },
    "token": {
        "access_token": "ya29.Glv-Bi1c0n5G_WE-G1oZW9Ec45JS01H6AFJWE3ki9fNTm3wLuan3JYG5bUI0cu9bfAX2wt9-t7sqIM8vyYp_bke1gLhOPf03iiR868XSUvhOECzNAYLwHY_wafK1",
        "expiry_date": 1556137112472,
        "refresh_token": "1/Wt6JQy6e5LqAccwu7SwW_GIQtyyp43BQlEXSwdsf-lE",
        "scope": "https://www.googleapis.com/auth/calendar.readonly",
        "token_type": "Bearer"
    }
}*/