/*
* File: BlockBorsa.kt
* Version: 1.0.0
* Date: 2019-02-17
* Author: Gian Marco Bratzu
* License:
*
* History:
* Author                || Date         || Description
* Gian Marco Bratzu     || 2019-02-17   || Writing class BlockBorsa
* Matteo Depascale      || 2019-02-23   || Verifying code
*/

package com.megalexa.models.blocks

import com.megalexa.R
import com.megalexa.models.connectors.Connector
import com.megalexa.models.connectors.ConnectorBorsa
import com.megalexa.util.ApplicationContextProvider
import com.megalexa.util.InvalidBlockException


class BlockBorsa(private val url: String): Block,Filtrable {
    init{
        val connector = generateConnector(url)

    }


    /** generateConnector(url) returns an object that represents the connector for the desidered feed for Borsa
     * @param url is the url for the feedRSS
     * @return ConnectorBorsa for the url(if the URL is valid)
     */
    private fun generateConnector(url: String): Connector {

        val toReturn=ConnectorBorsa(url= url)
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
        val message= ApplicationContextProvider.context!!.resources!!.getString(R.string.BlockBorsa)
        return "$message $url + URL "
    }

    fun url()= url

}