package com.megalexa.util.service

import com.megalexa.models.blocks.BlockPin
import org.json.JSONObject

object BlockPinService : BlockService() {

    override fun convertFromJSON(jsonObject: JSONObject): BlockPin {
        val pin = jsonObject.getJSONObject("config").getString("PIN").toInt()
        return BlockPin(pin)
    }

    override fun <BlockPin> convertToJSON(t: BlockPin, userID: String): JSONObject {
        val pinBlock = t as com.megalexa.models.blocks.BlockPin
        val allBlock = JSONObject()
        allBlock.put("blockType", "PIN")
        val config = JSONObject()
        config.put("PIN", pinBlock.pin().toString() )
        allBlock.put("config", config)
        return allBlock
    }
}