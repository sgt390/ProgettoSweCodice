package com.megalexa.models.blocks

import com.megalexa.R
import com.megalexa.util.ApplicationContextProvider

class BlockCalendar(token :String): Block, Filtrable {
    //configuration
    private val auth_provider_url = "https://www.googleapis.com/oauth2/v1/certs"
    private val auth_uri = "https://accounts.google.com/o/oauth2/auth"
    private val project_id = "megalexa-1556132707047"
    private val redirect_uris1 = "urn:ietf:wg:oauth:2.0:oob"
    private val redirect_uris2 = "http://localhost"
    private val token_uri = "https://oauth2.googleapis.com/token"
    private val google_client_id = ApplicationContextProvider.context!!.resources!!.getString(R.string.google_client_id)
    private val google_client_secret = ApplicationContextProvider.context!!.resources!!.getString(R.string.google_client_secret)
    //token
    private val expiresIn = 100000
    private val scope = "https://www.googleapis.com/auth/gmail.send"
    private val tokenType = "Bearer"
    private val access_token = token

    override fun getInformation() :String {
        return  ApplicationContextProvider.context!!.resources!!.getString(R.string.BlockCalendar)
    }

    fun testConnection(): Boolean{
        TODO("not implemented")
    }

    fun getAuthProvider() = auth_provider_url
    fun getAuthUri() = auth_uri
    fun getProjectId() = project_id
    fun getRedirect1() = redirect_uris1
    fun getRedirect2() = redirect_uris2
    fun getTokenUri() = token_uri
    fun getClientId() = google_client_id
    fun getClientSecret() = google_client_secret
    fun getDate() = expiresIn
    fun getScope() = scope
    fun getTokenType() = tokenType
    fun getToken() = access_token

}