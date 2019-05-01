/*
* File: BlockNews.kt
* Version: 1.0.0
* Date:
* Author: Gian Marco Bratzu
* License:
*
* History:
* Author                || Date         || Description
* Gian Marco Bratzu     ||    || Writing class BlockNews

*/

package com.megalexa.models.blocks

import android.util.Log
import com.megalexa.models.connectors.Connector
import com.megalexa.models.connectors.ConnectorNews
import org.json.JSONObject


class BlockNews(private val url: String): Block,Filtrable {
    init{
        val connector = generateConnector(url)

    }


    /** generateConnector(url) returns an object that represents the connector for the desidered feed for News
     * @param url is the url for the feedRSS
     * @return ConnectorNews for the url(if the URL is valid)
     */
    private fun generateConnector(url: String): Connector {

        val toReturn=ConnectorNews(url= url)
        if(!toReturn.valid()){
            //throw InvalidBLockException() TODO("custom error handling required ")
        }

        return toReturn

    }


    /** getInformation()
     * @return  String that sums up the information for the given feedRSS
     *
     */
    override fun getInformation():String {
        return "News"
    }

    fun url()= url

}