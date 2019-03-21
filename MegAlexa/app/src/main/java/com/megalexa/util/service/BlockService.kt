package com.megalexa.util.service

import com.megalexa.models.blocks.Block
import org.json.JSONObject

abstract class BlockService : Service() {

    abstract fun convertFromJSON(jsonObject: JSONObject): Block

    abstract fun convertToJSON(block:Block): JSONObject

    override val resource: String
        get() = "block"

}