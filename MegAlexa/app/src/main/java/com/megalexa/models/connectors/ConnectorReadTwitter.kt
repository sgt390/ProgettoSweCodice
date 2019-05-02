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
import io.github.rybalkinsd.kohttp.ext.httpGet
import okhttp3.Response


class ConnectorReadTwitter(private val username: String):Connector {
    //private val url = "https://twitter.com/users/username_available?username="
    private val url = "https://api.twitter.com/1.1/users/search.json?q="
    override fun connect(url: String):String {
        TODO()
    }


    override fun valid():Boolean {
        val check = url + username
        val response: Response = check.httpGet()
        /*val response: Response = check.httpGet().responseJSON { _, _, result ->
            result.get().obj() // here you have your JSON object
        }*/
        val result = JSONObject(response.message())

        return true
    }

}