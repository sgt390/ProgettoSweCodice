package com.megalexa.util.service

import com.megalexa.models.blocks.BlockTwitter
import org.json.JSONObject

object BlockWriteTweetService :BlockService() {


    override fun convertFromJSON(jsonObject: JSONObject): BlockTwitter {
        return BlockTwitter(jsonObject.getJSONObject("config").getString("TweetText"))
    }

    override fun <BlockTwitter> convertToJSON(t: BlockTwitter): JSONObject {
        val blockTwitter = t as com.megalexa.models.blocks.BlockTwitter
        val allBlock = JSONObject()
        allBlock.put("blockType", "TwitterWrite")
        val config = JSONObject()
        config.put("access_token_key", blockTwitter.getAccessKey())
        config.put("access_token_secret", blockTwitter.getAccessSecret())
        config.put("consumer_key", blockTwitter.getConsumerKey())
        config.put("consumer_secret", blockTwitter.getConsumerSecret())
        config.put("TweetText", blockTwitter.getScreenName())
        allBlock.put("config", config)
        return allBlock
    }
}