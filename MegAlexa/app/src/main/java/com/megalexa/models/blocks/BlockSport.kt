/*
* File: BlockSport.kt
* Version: 1.0.0
* Date:
* Author: Gian Marco Bratzu
* License:
*
* History:
* Author                || Date         || Description
* Gian Marco Bratzu     ||    || Writing class BlockSport

*/

package com.megalexa.models.blocks

import android.util.Log
import com.megalexa.models.connectors.Connector
import com.megalexa.models.connectors.ConnectorSport
import org.json.JSONObject


class BlockSport(private val url: String): Block,Filtrable {
    init{
        val connector = generateConnector(url)

    }


    /** generateConnector(url) returns an object that represents the connector for the desidered feed for Sport
     * @param url is the url for the feedRSS
     * @return ConnectorSport for the url(if the URL is valid)
     */
    private fun generateConnector(url: String): Connector {

        val toReturn=ConnectorSport(url= url)
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
        return " Sport Feed "
    }

    override fun toJSON() : JSONObject {
        val allBlock = JSONObject()
        allBlock.put("blockType", "Sport" )
        val config = JSONObject()
        config.put("url" , url)
        allBlock.put("config", config)
        return allBlock
    }

    fun url()= url

}