/*
 *
 *  File name: BlockTwitterHashtagService.kt
 *  Version: 1.0.0
 *  Date: 2019-04-20
 *  Author: Andrea Deidda
 *  License:
 *  History:
 *  Author        || Date            || Description
 *  Andrea Deidda || 2019-04-20      || File created
 *  Mirko Franco  || 2019-04-27      || Verifying code
 *
 */

package com.megalexa.util.service

import com.megalexa.models.blocks.BlockTwitterHashtag
import org.json.JSONObject

object BlockTwitterHashtagService :BlockService() {


    override fun convertFromJSON(jsonObject: JSONObject): BlockTwitterHashtag {
        return BlockTwitterHashtag(jsonObject.getJSONObject("config").getString("hashtag"))
    }

    override fun <BlockTwitterHashtag> convertToJSON(t: BlockTwitterHashtag): JSONObject {
        val blockTwitter = t as com.megalexa.models.blocks.BlockTwitterHashtag
        val allBlock = JSONObject()
        allBlock.put("blockType", "TwitterHashtag")
        val config = JSONObject()
        config.put("access_token_key", blockTwitter.getAccessKey())
        config.put("access_token_secret", blockTwitter.getAccessSecret())
        config.put("consumer_key", blockTwitter.getConsumerKey())
        config.put("consumer_secret", blockTwitter.getConsumerSecret())
        config.put("hashtag", blockTwitter.getHashtag())
        allBlock.put("config", config)
        return allBlock
    }
}