/*
 *
 *  File name: BlockCalendarService.kt
 *  Version: 1.0.0
 *  Date: 2019-05-02
 *  Author:
 *  License:
 *  History:
 *  Author            || Date            || Description
 *  Gian Marco Bratzu || 2019-05-02      || File created
 *  Mirko Franco      || 2019-05-04      || Verifying code
 *
 */

package com.megalexa.util.service

import com.megalexa.models.blocks.BlockCalendar
import org.json.JSONArray
import org.json.JSONObject

object BlockCalendarService: BlockService() {

    override fun convertFromJSON(jsonObject: JSONObject): BlockCalendar {
        val access_token : String = jsonObject.getJSONObject("config").getJSONObject("token").get("access_token").toString()
        val refresh_token : String = jsonObject.getJSONObject("config").getJSONObject("token").get("refresh_token").toString()
        return BlockCalendar(access_token,refresh_token)
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
        token.put("refresh_token", blockCalendar.getRefreshToken())
        token.put("scope", blockCalendar.getScope())
        token.put("token_type",blockCalendar.getTokenType())
        return token
    }
}
