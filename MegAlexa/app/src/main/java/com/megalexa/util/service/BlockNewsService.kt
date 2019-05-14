/*
 *
 *  File name:
 *  Version:
 *  Date:
 *  Author:
 *  License:
 *  History:
 *  Author        || Date            || Description
 * /
 *
 */

package com.megalexa.util.service

import com.megalexa.models.blocks.BlockNews
import org.json.JSONObject

object BlockNewsService :BlockService() {

    override fun convertFromJSON(jsonObject: JSONObject): BlockNews {
        return BlockNews(jsonObject.getJSONObject("config").getString("URL"))
    }

    override fun <BlockNews> convertToJSON(t: BlockNews): JSONObject {
        val blockNews = t as com.megalexa.models.blocks.BlockNews
        val allBlock = JSONObject()
        allBlock.put("blockType", "News" )
        val config = JSONObject()
        config.put("URL" , blockNews.url())
        allBlock.put("config", config)
        return allBlock

    }
}