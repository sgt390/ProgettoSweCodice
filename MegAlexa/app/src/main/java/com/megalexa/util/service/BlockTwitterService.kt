package com.megalexa.util.service

import android.security.keystore.StrongBoxUnavailableException
import com.megalexa.models.blocks.BlockSport
import org.json.JSONObject

object BlockTwitterService :BlockService() {

    override fun convertFromJSON(jsonObject: JSONObject): BlockSport {
        TODO()
    }

    override fun <T> convertToJSON(t: T, userID : String): JSONObject {
        TODO()
    }
}