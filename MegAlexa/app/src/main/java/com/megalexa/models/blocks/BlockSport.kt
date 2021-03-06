/*
* File: BlockSport.kt
* Version: 1.0.0
* Date: 2019-02-18
* Author: Gian Marco Bratzu
* License:
*
* History:
* Author                || Date         || Description
* Gian Marco Bratzu     || 2019-02-18   || Writing class BlockSport
* Matteo Depascale      || 2019-02-23   || Verifying code
*/

package com.megalexa.models.blocks

import com.megalexa.R
import com.megalexa.models.connectors.Connector
import com.megalexa.models.connectors.ConnectorSport
import com.megalexa.util.ApplicationContextProvider
import com.megalexa.util.InvalidBlockException


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
            throw InvalidBlockException("Invalid Block")
        }

        return toReturn

    }


    /** getInformation()
     * @return  String that sums up the information for the given feedRSS
     *
     */
    override fun getInformation():String {
        return  ApplicationContextProvider.context!!.resources!!.getString(R.string.BlockSport)
    }

    fun url()= url

}