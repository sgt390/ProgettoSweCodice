package com.megalexa.util.service

import com.megalexa.models.blocks.BlockTwitter
import com.megalexa.models.blocks.BlockTwitterWrite
import org.json.JSONObject

object BlockWriteTweetService :BlockService() {


    override fun convertFromJSON(jsonObject: JSONObject): BlockTwitterWrite {
        return BlockTwitterWrite()
    }

    override fun <BlockTwitterWrite> convertToJSON(t: BlockTwitterWrite): JSONObject {
        val blockTwitter = t as com.megalexa.models.blocks.BlockTwitterWrite
        val allBlock = JSONObject()
        allBlock.put("blockType", "TwitterWrite")
        val config = JSONObject()
        config.put("access_token_key", blockTwitter.getAccessKey())
        config.put("access_token_secret", blockTwitter.getAccessSecret())
        config.put("consumer_key", blockTwitter.getConsumerKey())
        config.put("consumer_secret", blockTwitter.getConsumerSecret())
        allBlock.put("config", config)
        return allBlock
    }
}