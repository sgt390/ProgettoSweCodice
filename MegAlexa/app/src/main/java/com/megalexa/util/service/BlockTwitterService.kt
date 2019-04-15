package com.megalexa.util.service

import com.megalexa.models.blocks.BlockSport
import org.json.JSONObject

object BlockTwitterService :BlockService() {

    override fun convertFromJSON(jsonObject: JSONObject): BlockSport { TODO() }

    override fun <T> convertToJSON(t: T): JSONObject { TODO() }

    /*
    override fun convertFromJSON(jsonObject: JSONObject): BlockTwitter {
        val access_token_key = json.getJSONObject("config").getString("access_token_key")
        val access_token_secret = json.getJSONObject("config").getString("access_token_secret")
        val consumer_key = json.getJSONObject("config").getString("consumer_key")
        val consumer_secret = json.getJSONObject("config").getString("consumer_secret")
        val screenName = json.getJSONObject("config").getString("screenName")

        return BlockTwitter(access_token_key,access_token_secret,consumer_key
                            consumer_secret,screenName)
    }

    override fun <BlockTwitter> convertToJSON(t: BlockTwitter): JSONObject {
        val blockTwitter = t as com.megalexa.models.blocks.BlockTwitter
        val allBlock = JSONObject()
        allBlock.put("blockType", "Twitter")
        val config = JSONObject()
        config.put("access_token_key", blockTwitter.get)
        config.put("access_token_secret", blockTwitter.get)
        config.put("consumer_key", blockTwitter.get)
        config.put("consumer_secret", blockTwitter.get)
        config.put("screenName", blockTwitter.get)
        allBlock.put("config", config)
        return allBlock
    }
    */
}