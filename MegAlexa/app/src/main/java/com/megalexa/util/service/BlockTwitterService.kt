package com.megalexa.util.service

import com.megalexa.models.blocks.BlockSport
import org.json.JSONObject

object BlockTwitterService :BlockService() {

    override fun convertFromJSON(jsonObject: JSONObject): BlockSport {
        TODO()
    }

    override fun <T> convertToJSON(t: T): JSONObject {
        TODO()
    }
}