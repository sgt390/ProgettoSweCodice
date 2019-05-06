package com.megalexa.util.service

import android.util.Log
import com.google.api.client.auth.oauth2.Credential
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.services.gmail.Gmail
import com.megalexa.models.blocks.BlockReadEmail
import com.megalexa.util.GmailQuickStart
import org.json.JSONObject
import java.io.IOException
import java.security.GeneralSecurityException

object BlockReadEmailService:BlockService() {

    override fun convertFromJSON(jsonObject: JSONObject): BlockReadEmail {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @Throws(IOException::class, GeneralSecurityException::class)
    override fun <BlockReadEmail> convertToJSON(t: BlockReadEmail): JSONObject {

        val blockMail = t as com.megalexa.models.blocks.BlockReadEmail
        val allBlock = JSONObject()
        allBlock.put("blockType", "Mail")
        val config = JSONObject()
        config.put("URL", "blockMail")
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

}