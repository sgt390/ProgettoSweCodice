package com.megalexa.util

import android.util.Log
import com.google.api.client.auth.oauth2.Credential
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.client.util.store.FileDataStoreFactory
import com.google.api.services.gmail.GmailScopes
import java.io.*

import java.util.Collections

class GmailQuickStart {

    private val JSON_FACTORY: JacksonFactory = JacksonFactory.getDefaultInstance()
    private val TOKENS_DIRECTORY_PATH: String = "tokens"

    private val SCOPES: List<String> = Collections.singletonList(GmailScopes.GMAIL_LABELS)
    private val CREDENTIALS_FILE_PATH: String = "/credentials.json"

    @Throws(IOException::class)
    fun getCredential(HTTP_TRANSPORT: NetHttpTransport): Credential{
        Log.d("arrivato","1")
        val input = this::class.java.getResourceAsStream(CREDENTIALS_FILE_PATH)
            ?: throw FileNotFoundException("Resource not found: $CREDENTIALS_FILE_PATH")
        Log.d("arrivato","2")
        val clientSecrets: GoogleClientSecrets = GoogleClientSecrets.load(JSON_FACTORY, InputStreamReader(input))
        Log.d("arrivato","3")
        val flow: GoogleAuthorizationCodeFlow = GoogleAuthorizationCodeFlow.Builder(
            HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
            //.setDataStoreFactory(FileDataStoreFactory(File(TOKENS_DIRECTORY_PATH)))
            .setAccessType("offline").build()
        Log.d("arrivato","4")
        val receiver: LocalServerReceiver = LocalServerReceiver.Builder().setPort(8888).build()
        Log.d("arrivato","5")
        val Tok : Credential = AuthorizationCodeInstalledApp(flow,receiver).authorize("user")
        Log.d("arrivato","6")
        return AuthorizationCodeInstalledApp(flow, receiver).authorize("user")
    }
}