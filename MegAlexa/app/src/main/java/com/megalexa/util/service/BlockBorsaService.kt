package com.megalexa.util.service

import com.megalexa.models.blocks.Block
import com.megalexa.models.blocks.BlockBorsa
import org.json.JSONObject

object BlockBorsaService :BlockService() {

    override fun <BlockBorsa> convertFromJSON(jsonObject: JSONObject): BlockBorsa {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <BlockBorsa> convertToJSON(t: BlockBorsa): JSONObject {
        val blockBorsa = t as com.megalexa.models.blocks.BlockBorsa
        val allBlock = JSONObject()
        allBlock.put("blockType", "Borsa" )
        val config = JSONObject()
        config.put("url" , blockBorsa.url())
        allBlock.put("config", config)
        return allBlock

    }
}