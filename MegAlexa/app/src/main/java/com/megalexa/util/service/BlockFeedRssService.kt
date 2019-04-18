package com.megalexa.util.service

import com.megalexa.models.blocks.BlockFeedRss
import org.json.JSONObject

object BlockFeedRssService :BlockService() {

    override fun convertFromJSON(jsonObject: JSONObject): BlockFeedRss {
        return BlockFeedRss(jsonObject.getJSONObject("config").getString("URL"))
    }

    override fun <BlockFeedRss> convertToJSON(t: BlockFeedRss): JSONObject {
        val blockFeedRss= t as com.megalexa.models.blocks.BlockFeedRss
        val allBlock = JSONObject()
        allBlock.put("blockType", "FeedRSS" )
        val config = JSONObject()
        config.put("URL" , blockFeedRss.url())
        allBlock.put("config", config)
        return allBlock

    }
}