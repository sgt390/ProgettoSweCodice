package com.megalexa.util.service

import com.megalexa.models.blocks.Block
import com.megalexa.models.blocks.BlockFeedRss
import org.json.JSONObject

object BlockFeedRssService :BlockService() {

    override fun convertFromJSON(jsonObject: JSONObject): Block {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun convertToJSON(block: Block): JSONObject {
        val blockFeedRss= block as BlockFeedRss
        val allBlock = JSONObject()
        allBlock.put("blockType", "FeedRSS" )
        val config = JSONObject()
        config.put("url" , blockFeedRss.url())
        allBlock.put("config", config)
        return allBlock

    }
}