package com.megalexa.util.service

import com.megalexa.models.blocks.BlockSport
import org.json.JSONObject

object BlockTwitterService :BlockService() {

    override fun convertFromJSON(jsonObject: JSONObject): BlockSport { TODO() }

    override fun <T> convertToJSON(t: T): JSONObject { TODO() }

    /*
    override fun convertFromJSON(jsonObject: JSONObject): BlockTwitter {
        return BlockTwitter(json.getJSONObject("config").getString("screenName"))
    }

    override fun <BlockTwitter> convertToJSON(t: BlockTwitter): JSONObject {
        val blockTwitter = t as com.megalexa.models.blocks.BlockTwitter
        val allBlock = JSONObject()
        allBlock.put("blockType", "Twitter")
        val config = JSONObject()
        //le 4 seguenti chiavi come campi statici nella classe blocco
            dato che sempre uguali(?)
        config.put("access_token_key", blockTwitter.getAccessKey())
        config.put("access_token_secret", blockTwitter.getAccessSecret())
        config.put("consumer_key", blockTwitter.getConsumerKey())
        config.put("consumer_secret", blockTwitter.getConsumerSecret())
        config.put("screenName", blockTwitter.getScreenName())
        allBlock.put("config", config)
        return allBlock
    }
    */
}