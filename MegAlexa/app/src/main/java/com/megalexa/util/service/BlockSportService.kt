package com.megalexa.util.service

import com.megalexa.models.blocks.Block
import com.megalexa.models.blocks.BlockSport
import org.json.JSONObject

object BlockSportService :BlockService() {

    override fun convertFromJSON(jsonObject: JSONObject): BlockSport {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <BlockSport> convertToJSON(t: BlockSport): JSONObject {
        val blockSport = t as com.megalexa.models.blocks.BlockSport
        val allBlock = JSONObject()
        allBlock.put("blockType", "Sport" )
        val config = JSONObject()
        config.put("url" , blockSport.url())
        allBlock.put("config", config)
        return allBlock

    }
}