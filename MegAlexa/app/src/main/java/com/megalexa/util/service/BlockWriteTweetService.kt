package com.megalexa.util.service

import com.megalexa.models.blocks.BlockTwitterWrite
import com.twitter.sdk.android.core.TwitterCore
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
        val session = TwitterCore.getInstance().sessionManager.activeSession
        val authToken = session.getAuthToken();
        val token = authToken.token
        val secret = authToken.secret
        config.put("access_token_key", token )
        config.put("access_token_secret", secret)
        config.put("consumer_key", blockTwitter.getConsumerKey())
        config.put("consumer_secret", blockTwitter.getConsumerSecret())
        allBlock.put("config", config)
        return allBlock
    }
}