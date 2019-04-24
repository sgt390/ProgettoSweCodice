package com.megalexa.util

import android.app.Application
import android.content.Context

class ApplicationContextProvider : Application() {

    override fun onCreate() {
        super.onCreate()

        context = applicationContext

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
