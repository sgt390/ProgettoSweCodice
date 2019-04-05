package com.megalexa.util.service

import com.megalexa.models.blocks.Filter
import org.json.JSONObject

object FilterService: BlockService() {
    override fun convertFromJSON(jsonObject: JSONObject): Filter {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <T> convertToJSON(t: T): JSONObject {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}