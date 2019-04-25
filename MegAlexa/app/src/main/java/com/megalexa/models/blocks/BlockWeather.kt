/*
* File: BlockWeather.kt
* Version: 1.0.0
* Date:
* Author: Gian Marco Bratzu
* License:
*
* History:
* Author                || Date         || Description
* Gian Marco Bratzu     ||    || Writing class BlockWeather

*/

package com.megalexa.models.blocks

import android.util.Log
import com.megalexa.models.connectors.Connector
import com.megalexa.models.connectors.ConnectorWeather
import org.json.JSONObject


class BlockWeather(private val url: String): Block,Filtrable {
    init{
        val connector = generateConnector(url)

    }


    /** generateConnector(url) returns an object that represents the connector for the desidered feed for Weather
     * @param url is the url for the feedRSS
     * @return ConnectorWeather for the url(if the URL is valid)
     */
    private fun generateConnector(url: String): Connector {

        val toReturn=ConnectorWeather(url= url)
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
        return "Feed Weather block created for $url URL "
    }

    override fun toJSON() : JSONObject {
        val allBlock = JSONObject()
        allBlock.put("blockType", "Weather" )
        val config = JSONObject()
        config.put("url" , url)
        allBlock.put("config", config)
        return allBlock
    }

    fun url()= url

}