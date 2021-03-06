/*
 *
 *  File name: BlockTwitterHomeTLService.kt
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

import com.megalexa.models.blocks.BlockTwitterHomeTL
import com.twitter.sdk.android.core.TwitterCore
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