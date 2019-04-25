package com.megalexa.models.blocks

import android.app.admin.DevicePolicyManager
import android.util.Log
import com.google.gson.JsonObject
import com.megalexa.R
import com.megalexa.util.ApplicationContextProvider
import com.twitter.sdk.android.core.DefaultLogger
import com.twitter.sdk.android.core.Twitter
import com.twitter.sdk.android.core.TwitterAuthConfig
import com.twitter.sdk.android.core.TwitterConfig
import org.json.JSONObject

class BlockTwitterAuthentication(private val username : String, private val password : String) : Block {


    private lateinit var authConfig: TwitterAuthConfig
    private lateinit var config : TwitterConfig

    fun authenticate() {
        authConfig = TwitterAuthConfig(ApplicationContextProvider.context?.resources?.getString(R.string.consumer_api_key_twitter),
            ApplicationContextProvider.context?.resources?.getString(R.string.consumer_api_key_secret_twitter))
        config = TwitterConfig.Builder(ApplicationContextProvider.context)
            .logger(DefaultLogger(Log.DEBUG))
            .twitterAuthConfig(authConfig)
            .debug(true)
            .build()
        Twitter.initialize(config)

    }

    override fun getInformation() : String {
        return "Twitter Authentication"
    }

    override fun toJSON(): JSONObject {
        return JSONObject()
    }
}