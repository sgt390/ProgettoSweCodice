package com.megalexa.util

import com.google.api.client.auth.oauth2.Credential
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.JsonFactory
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.client.util.store.FileDataStoreFactory
import com.google.api.services.gmail.Gmail
import com.google.api.services.gmail.GmailRequest
import com.google.api.services.gmail.GmailScopes
import com.google.api.services.gmail.model.Label
import com.google.api.services.gmail.model.ListLabelsResponse
import java.io.*

import java.security.GeneralSecurityException
import java.util.Collections

class GmailQuickStart {
    private lateinit var HTTP_TRANSPORT: NetHttpTransport
    init {
        HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport()
        var service = Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, this.getCredentials(HTTP_TRANSPORT))
            .setApplicationName(APPLICATION_NAME)
            .build();
    }
    private val APPLICATION_NAME: String = "Gmail API Java Quickstart"
    private val JSON_FACTORY: JacksonFactory = JacksonFactory.getDefaultInstance()
    private val TOKENS_DIRECTORY_PATH: String = "tokens"

    private val SCOPES: List<String> = Collections.singletonList(GmailScopes.GMAIL_LABELS)
    private val CREDENTIALS_FILE_PATH: String = "/credentials.json"

    private fun getCredential(HTTP_TRANSPORT: NetHttpTransport): Credential{
        val input: InputStream = GmailQuickStart.
        val clientSecrets: GoogleClientSecrets = GoogleClientSecrets.load(JSON_FACTORY, InputStreamReader(input))

        val flow: GoogleAuthorizationCodeFlow = GoogleAuthorizationCodeFlow.Builder(
            HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
            .setDataStoreFactory(FileDataStoreFactory(File(TOKENS_DIRECTORY_PATH)))
            .setAccessType("offline").build()
        val receiver: LocalServerReceiver = LocalServerReceiver.Builder().setPort(8888).build()
        return AuthorizationCodeInstalledApp(flow, receiver).authorize("user")
    }
}