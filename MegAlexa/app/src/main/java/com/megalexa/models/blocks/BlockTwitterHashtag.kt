
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


class BlockTwitterHashtag(private val screenName: String): Block,Filtrable {

    private val consumer_key = ApplicationContextProvider.context!!.getResources()!!.getString(R.string.consumer_api_key_twitter)//"INSERT CONSUMER API KEYS"
    private val consumer_secret = ApplicationContextProvider.context!!.resources!!.getString(R.string.consumer_api_key_secret_twitter) //"INSERT CONSUMER API KEYS SECRET"
    private val access_token_key = ApplicationContextProvider.context!!.resources!!.getString(R.string.access_token_twitter)//"INSERT ACCESS TOKEN"
    private val access_token_secret = ApplicationContextProvider.context!!.resources!!.getString(R.string.access_token_secret_twitter)//"INSERT ACCESS TOKEN SECRET"

    override fun getInformation():String {
        return "Twitter hashtag block created "
    }

    fun getAccessKey()=access_token_key
    fun getAccessSecret()=access_token_secret
    fun getConsumerKey()=consumer_key
    fun getConsumerSecret()=consumer_secret
    fun getHashtag() = screenName


}