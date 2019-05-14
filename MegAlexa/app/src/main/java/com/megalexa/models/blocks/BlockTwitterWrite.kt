/*
 *
 *  File name: BlockTwitterWrite.kt
 *  Version: 1.0.0
 *  Date: 2019-04-16
 *  Author: Mirko Franco
 *  License:
 *  History:
 *  Author            || Date            || Description
 *  Mirko Franco      || 2019-04-16      || File created
 *  Gian Marco Bratzu || 2019-04-17   || Verifying code
 */

package com.megalexa.models.blocks

import com.megalexa.R
import com.megalexa.util.ApplicationContextProvider

class BlockTwitterWrite(): Block,Filtrable {
    private val consumer_key = ApplicationContextProvider.context!!.getResources()!!.getString(R.string.consumer_api_key_twitter)//"INSERT CONSUMER API KEYS"
    private val consumer_secret = ApplicationContextProvider.context!!.resources!!.getString(R.string.consumer_api_key_secret_twitter) //"INSERT CONSUMER API KEYS SECRET"
    private val access_token_key = ApplicationContextProvider.context!!.resources!!.getString(R.string.access_token_twitter)//"INSERT ACCESS TOKEN"
    private val access_token_secret = ApplicationContextProvider.context!!.resources!!.getString(R.string.access_token_secret_twitter)//"INSERT ACCESS TOKEN SECRET"

    override fun getInformation():String {
        return ApplicationContextProvider.context!!.resources!!.getString(R.string.BlockTwitterWrite)
    }

    fun getAccessKey()=access_token_key
    fun getAccessSecret()=access_token_secret
    fun getConsumerKey()=consumer_key
    fun getConsumerSecret()=consumer_secret

}