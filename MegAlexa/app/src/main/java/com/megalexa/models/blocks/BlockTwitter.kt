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

import com.megalexa.R
import com.megalexa.util.ApplicationContextProvider
import org.json.JSONObject


class BlockTwitter(private val screenName: String): Block,Filtrable {

    private val consumer_key = ApplicationContextProvider.context!!.getResources()!!.getString(R.string.consumer_api_key_twitter)//"INSERT CONSUMER API KEYS"
    private val consumer_secret = ApplicationContextProvider.context!!.resources!!.getString(R.string.consumer_api_key_secret_twitter) //"INSERT CONSUMER API KEYS SECRET"
    private val access_token_key = ApplicationContextProvider.context!!.resources!!.getString(R.string.access_token_twitter)//"INSERT ACCESS TOKEN"
    private val access_token_secret = ApplicationContextProvider.context!!.resources!!.getString(R.string.access_token_secret_twitter)//"INSERT ACCESS TOKEN SECRET"

    //private val consumer_key = " "
    //private val consumer_secret =" "
    //private val access_token_key = " "
    //private val access_token_secret =" "

    override fun getInformation():String {
        return "Twitter user block created"
    }

    fun getAccessKey()=access_token_key
    fun getAccessSecret()=access_token_secret
    fun getConsumerKey()=consumer_key
    fun getConsumerSecret()=consumer_secret
    fun getScreenName() = screenName


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
