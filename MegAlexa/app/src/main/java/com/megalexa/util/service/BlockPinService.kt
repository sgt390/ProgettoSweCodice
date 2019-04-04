package com.megalexa.util.service

import com.megalexa.models.blocks.BlockPin
import org.json.JSONObject

object BlockPinService : BlockService() {

    override fun convertFromJSON(jsonObject: JSONObject): BlockPin {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <BlockPin> convertToJSON(t: BlockPin): JSONObject {
        val pinBlock = t as com.megalexa.models.blocks.BlockPin
        val allBlock = JSONObject()
        allBlock.put("blockType", "Pin")
        val config = JSONObject()
        config.put("Pin", pinBlock.pin() )
        allBlock.put("config", config)
        return allBlock
    }
}