package com.megalexa.util.service

import com.megalexa.models.blocks.BlockTwitterHomeTL
import org.json.JSONObject

object BlockTwitterHomeTLService :BlockService() {


    override fun convertFromJSON(jsonObject: JSONObject): BlockTwitterHomeTL {
        return BlockTwitterHomeTL()
    }

    override fun <BlockTwitterHomeTL> convertToJSON(t: BlockTwitterHomeTL): JSONObject {
        val blockTwitter = t as com.megalexa.models.blocks.BlockTwitterHomeTL
        val allBlock = JSONObject()
        allBlock.put("blockType", "TwitterHomeTL")
        val config = JSONObject()
        config.put("access_token_key", blockTwitter.getAccessKey())
        config.put("access_token_secret", blockTwitter.getAccessSecret())
        config.put("consumer_key", blockTwitter.getConsumerKey())
        config.put("consumer_secret", blockTwitter.getConsumerSecret())
        allBlock.put("config", config)
        return allBlock
    }
}