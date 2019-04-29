package com.megalexa.util

import android.app.Application
import android.content.Context
import android.util.Log
import com.megalexa.R
import com.twitter.sdk.android.core.DefaultLogger
import com.twitter.sdk.android.core.Twitter
import com.twitter.sdk.android.core.TwitterAuthConfig
import com.twitter.sdk.android.core.TwitterConfig

class ApplicationContextProvider : Application() {

    override fun onCreate() {
        super.onCreate()

        context = applicationContext
        val authConfig = TwitterAuthConfig(
            resources.getString(R.string.consumer_api_key_twitter),
            resources.getString(R.string.consumer_api_key_secret_twitter)
        )
        val config = TwitterConfig.Builder(this)
            .logger(DefaultLogger(Log.DEBUG))
            .twitterAuthConfig(authConfig)
            .debug(true)
            .build()
        Twitter.initialize(config)
    }
    companion object {

        /**
         * Returns the application context
         *
         * @return application context
         */
        var context: Context? = null
            private set
    }

}
