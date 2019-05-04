package com.megalexa.util.service

//import com.google.api.client.auth.oauth2.Credential
//import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp
//import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver
//import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow
//import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets
//import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
//import com.google.api.client.http.javanet.NetHttpTransport
//import com.google.api.client.json.jackson2.JacksonFactory
//import com.google.api.client.util.DateTime
//import com.google.api.client.util.store.FileDataStoreFactory
//import com.google.api.services.calendar.Calendar
//import com.google.api.services.calendar.CalendarScopes
import org.json.JSONObject
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStreamReader
import java.security.GeneralSecurityException

object BlockReadEmailService:BlockService() {

    override fun convertFromJSON(jsonObject: JSONObject): BlockReadEmailService {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <BlockReadEmail> convertToJSON(t: BlockReadEmail): JSONObject {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}