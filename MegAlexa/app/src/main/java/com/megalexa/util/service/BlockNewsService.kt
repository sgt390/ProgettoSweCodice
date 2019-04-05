package com.megalexa.util.service

import com.megalexa.models.blocks.Block
import com.megalexa.models.blocks.BlockNews
import org.json.JSONObject

object BlockNewsService :BlockService() {

    override fun convertFromJSON(jsonObject: JSONObject): BlockNews {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <BlockNews> convertToJSON(t: BlockNews): JSONObject {
        val blockNews = t as com.megalexa.models.blocks.BlockNews
        val allBlock = JSONObject()
        allBlock.put("blockType", "News" )
        val config = JSONObject()
        config.put("url" , blockNews.url())
        allBlock.put("config", config)
        return allBlock

    }
}