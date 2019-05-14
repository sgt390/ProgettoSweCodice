/*
 *
 *  File name: BlockFeedRssService.kt
 *  Version: 1.0.0
 *  Date: 2019-03-12
 *  Author: Ludovico Brocca
 *  License:
 *  History:
 *  Author          || Date            || Description
 *  Ludovico Brocca || 2019-03-12      || File created
 *  Mirko Franco    || 2019-03-19      || Verifying code
 *
 */

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