package com.megalexa.viewModel

import com.megalexa.models.MegAlexa

class MegAlexaRepository private constructor(private val app :MegAlexa) {


    companion object {
        //Singleton Installation
        @Volatile private var instance: MegAlexaRepository? = null

        fun getInstance(app: MegAlexa) =
                instance ?: synchronized(this) {
                    instance ?: MegAlexaRepository(app).also{ instance=it }
                }
    }


    /**this functions sets the correct instance for the MegAlexa object
     * fetches from SharedInstances and decides if it' necessary to call api gateway
     * to read the correct instance
     */
    fun loadAppContext(userID:String) {
        TODO()
    }

    /**
     * saves information in SharedPreferences when the app closes
     */
    fun saveAppContext() {
        TODO()
    }
}