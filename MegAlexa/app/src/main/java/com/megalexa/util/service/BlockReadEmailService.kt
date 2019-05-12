package com.megalexa.util.service

import com.megalexa.models.blocks.BlockReadEmail
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.security.GeneralSecurityException

object BlockReadEmailService:BlockService() {

    override fun convertFromJSON(jsonObject: JSONObject): BlockReadEmail {
        return BlockReadEmail("token")
    }

    @Throws(IOException::class, GeneralSecurityException::class)
    override fun <BlockReadEmail> convertToJSON(t: BlockReadEmail): JSONObject {
        val allBlock = JSONObject()
        allBlock.put("blockType", "Email")
        val config = JSONObject()
        val credential = JSONObject()

        credential.put("installed", createInstalled(t))
        config.put("credentials", credential)
        config.put("token", createToken(t))
        allBlock.put("config", config)
        return allBlock
    }


    private fun <BlockReadEmail> createInstalled(t: BlockReadEmail): JSONObject{
        val installed = JSONObject()
        val blockMail = t as com.megalexa.models.blocks.BlockReadEmail
        installed.put("auth_provider_x509_cert_url", blockMail.getAuthProvider())
        installed.put("auth_uri", blockMail.getAuthUri())
        installed.put("client_id", blockMail.getClientId())
        installed.put("client_secret", blockMail.getClientSecret())
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
        token.put("access_token", blockMail.getToken())
        token.put("expiry_date", blockMail.getDate())
        token.put("refresh_token", "refreshtoken")
        token.put("scope", blockMail.getScope())
        token.put("token_type",blockMail.getTokenType())
        return token
    }
}