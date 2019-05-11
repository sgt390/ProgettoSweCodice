package com.megalexa.util.service

import android.util.Log
import com.google.api.client.auth.oauth2.Credential
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.services.gmail.Gmail
import com.megalexa.models.blocks.BlockReadEmail
import com.megalexa.util.GmailQuickStart
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.security.GeneralSecurityException

object BlockReadEmailService:BlockService() {

    override fun convertFromJSON(jsonObject: JSONObject): BlockReadEmail {
        return BlockReadEmail()
    }

    @Throws(IOException::class, GeneralSecurityException::class)
    override fun <BlockReadEmail> convertToJSON(t: BlockReadEmail): JSONObject {
        val allBlock = JSONObject()
        allBlock.put("blockType", "Mail")
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
       // val HTTP_TRANSPORT: NetHttpTransport = NetHttpTransport()
        Log.d("Entrata","2")
        val JSON_FACTORY: JacksonFactory = JacksonFactory.getDefaultInstance()
        Log.d("Entrata","3")
        val token = GmailQuickStart().getCredential(HTTP_TRANSPORT)
        Log.d("Entrata","4")
        var service = Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, token)
            .setApplicationName("Megalexa")
            .build()

        println("access_token= " + token.accessToken)
        println("expires in = " + token.expiresInSeconds!!)
        println("refresh_token= " + token.refreshToken)
        println("serverEncoded= " + token.tokenServerEncodedUrl)
        return token
    }

    private fun <BlockReadEmail> createInstalled(t: BlockReadEmail): JSONObject{
        val installed = JSONObject()
        val blockMail = t as com.megalexa.models.blocks.BlockReadEmail
        installed.put("auth_provider_x509_cert_url", blockMail.getAuthProvider())
        installed.put("auth_uri", blockMail.getAuthUri())
        installed.put("client_id", "DalSecret")
        installed.put("client_secret", "DalSecret")
        installed.put("project_id", blockMail.getProjectId())
        val redUris = JSONArray()
        redUris.put(blockMail.getRedirect1())
        redUris.put(blockMail.getRedirect2())
        installed.put("redirect_uris", redUris)
        installed.put("token_uri", blockMail.getTokenUri())

        return installed
    }

    private fun <BlockReadEmail> createToken(t: BlockReadEmail): JSONObject{
        val token = JSONObject()
        val blockMail = t as com.megalexa.models.blocks.BlockReadEmail
        token.put("access_token", "tokenDaSecret")
        token.put("expiry_date", blockMail.getDate())
        token.put("refresh_token", "refreshtoken")
        token.put("scope", blockMail.getScope())
        token.put("token_type",blockMail.getTokenType())
        return token
    }
}