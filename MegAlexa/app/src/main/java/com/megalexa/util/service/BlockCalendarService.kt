package com.megalexa.util.service

import com.megalexa.models.blocks.BlockCalendar
import org.json.JSONObject

object BlockCalendarService: BlockService() {

    override fun convertFromJSON(jsonObject: JSONObject): BlockCalendar {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <T> convertToJSON(t: T, userID : String): JSONObject {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}