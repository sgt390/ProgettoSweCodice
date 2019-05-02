/*
* File: ConnectorFeedRss.kt
* Version: 1.0.0
* Date: 2019-03-18
* Author: Gian Marco Bratzu
*
* License:
*
* History:
* Author                || Date         || Description
* Gian Marco Bratzu     || 2019-03-18   || Writing class ConnectorReadTwitter
*      ||    || Verifying code
*/

package com.megalexa.models.connectors

import org.json.JSONObject
import java.net.URL


class ConnectorReadTwitter(private val username: String):Connector {
    private val url = "https://twitter.com/users/username_available?username="
    override fun connect(url: String):String {
        TODO()
    }


    override fun valid():Boolean {
        val result: JSONObject
        URL(url+username).httpGet().responseJSON { _, _, result ->
            result.get().obj() // here you have your JSON object
        }
        return result.getBoolean("valid")
    }

}