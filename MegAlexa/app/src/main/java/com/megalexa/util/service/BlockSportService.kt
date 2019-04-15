package com.megalexa.util.service

import com.megalexa.models.blocks.BlockSport
import org.json.JSONObject

object BlockSportService :BlockService() {

    override fun convertFromJSON(jsonObject: JSONObject): BlockSport {
        return BlockSport(jsonObject.getJSONObject("config").getString("URL"))
    }

    override fun <BlockSport> convertToJSON(t: BlockSport): JSONObject {
        val blockSport = t as com.megalexa.models.blocks.BlockSport
        val allBlock = JSONObject()
        allBlock.put("blockType", "Sport" )
        val config = JSONObject()
        config.put("URL" , blockSport.url())
        allBlock.put("config", config)
        return allBlock

    }
}