package com.megalexa.util

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
        val input = this::class.java.getResourceAsStream(CREDENTIALS_FILE_PATH)
            ?: throw FileNotFoundException("Resource not found: $CREDENTIALS_FILE_PATH")
        val clientSecrets: GoogleClientSecrets = GoogleClientSecrets.load(JSON_FACTORY, InputStreamReader(input))

        val flow: GoogleAuthorizationCodeFlow = GoogleAuthorizationCodeFlow.Builder(
            HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
            //.setDataStoreFactory(FileDataStoreFactory(File(TOKENS_DIRECTORY_PATH)))
            .setAccessType("offline").build()
        val receiver: LocalServerReceiver = LocalServerReceiver.Builder().setPort(8888).build()
        val Tok : Credential = AuthorizationCodeInstalledApp(flow,receiver).authorize("user")
        return AuthorizationCodeInstalledApp(flow, receiver).authorize("user")
    }
}