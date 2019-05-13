package com.megalexa.util.service

import android.util.Log
import com.google.api.client.auth.oauth2.Credential
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.services.calendar.CalendarScopes
import com.megalexa.models.blocks.BlockCalendar
import org.json.JSONArray
import org.json.JSONObject
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStreamReader

object BlockCalendarService: BlockService() {

    override fun convertFromJSON(jsonObject: JSONObject): BlockCalendar {
        return BlockCalendar("token")
    }

    override fun <BlockCalendar> convertToJSON(t: BlockCalendar): JSONObject {
        val allBlock = JSONObject()
        allBlock.put("blockType", "Calendar")
        val config = JSONObject()
        val credential = JSONObject()

        credential.put("installed", createInstalled(t))
        config.put("credentials", credential)
        config.put("token", createToken(t))
        allBlock.put("config", config)
        return allBlock
    }



    private fun <BlockCalendar> createInstalled(t: BlockCalendar): JSONObject{
        val installed = JSONObject()
        val blockCalendar = t as com.megalexa.models.blocks.BlockCalendar
        installed.put("auth_provider_x509_cert_url", blockCalendar.getAuthProvider())
        installed.put("auth_uri", blockCalendar.getAuthUri())
        installed.put("client_id", blockCalendar.getClientId())
        installed.put("client_secret", blockCalendar.getClientSecret())
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
        token.put("access_token", blockCalendar.getToken())
        token.put("expires_in", blockCalendar.getDate())
        token.put("scope", blockCalendar.getScope())
        token.put("token_type",blockCalendar.getTokenType())
        return token
    }
}
