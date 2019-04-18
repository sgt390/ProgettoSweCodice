/*
* File: BlockTwitter.kt
* Version: 1.0.0
* Date:
* Author: Gian Marco Bratzu
* License:
*
* History:
* Author                || Date         || Description
* Gian Marco Bratzu     ||    || Writing class BlockTwitter

*/

package com.megalexa.models.blocks

import android.util.Log
import com.megalexa.R
import com.megalexa.models.connectors.Connector
import com.megalexa.models.connectors.ConnectorReadTwitter
import org.json.JSONObject


class BlockTwitter(private val screenName: String): Block,Filtrable {

    private val consumer_key = R.string.consumer_api_key_twitter //"INSERT CONSUMER API KEYS"
    private val consumer_secret = R.string.consumer_api_key_secret_twitter //"INSERT CONSUMER API KEYS SECRET"
    private val access_token_key = R.string.access_token_twitter //"INSERT ACCESS TOKEN"
    private val access_token_secret = R.string.access_token_secret_twitter //"INSERT ACCESS TOKEN SECRET"

    override fun getInformation():String {
        return "Twitter block created "
    }

    override fun toJSON() : JSONObject {
        val allBlock = JSONObject()
        allBlock.put("blockType", "Twitter" )
        val config = JSONObject()
        config.put("access_token_key" , access_token_key )
        config.put("access_token_secret" , access_token_secret)
        config.put("consumer_key" , consumer_key)
        config.put("consumer_secret" , consumer_secret)
        config.put("screenName" , screenName)
        allBlock.put("config", config)
        return allBlock
    }


}