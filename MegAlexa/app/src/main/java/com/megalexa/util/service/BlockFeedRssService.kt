package com.megalexa.util.service

import com.megalexa.models.blocks.Block
import com.megalexa.models.blocks.BlockFeedRss
import org.json.JSONObject

object BlockFeedRssService :BlockService() {

    override fun <BlockFeedRss> convertFromJSON(jsonObject: JSONObject): BlockFeedRss {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <BlockFeedRss> convertToJSON(t: BlockFeedRss): JSONObject {
        val blockFeedRss= t as com.megalexa.models.blocks.BlockFeedRss
        val allBlock = JSONObject()
        allBlock.put("blockType", "FeedRSS" )
        val config = JSONObject()
        config.put("url" , blockFeedRss.url())
        allBlock.put("config", config)
        return allBlock

    }
}