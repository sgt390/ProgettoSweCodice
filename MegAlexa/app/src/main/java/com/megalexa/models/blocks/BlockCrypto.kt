/*
 *
 *  File name: BlockCrypto
 *  Version: 1.0
 *  Date: 2019-03-08
 *  Author: Ludovico Brocca
 *  License:
 *  History:
 *  Author            || Date            || Description
 *  Ludovico Brocca   || 2019-03-08      || File created
 *  Mirko Franco      || 2019-03-10   || Verifying code
 */



package com.megalexa.models.blocks

import com.megalexa.R
import com.megalexa.models.connectors.Connector
import com.megalexa.models.connectors.ConnectorCrypto
import com.megalexa.util.ApplicationContextProvider
import com.megalexa.util.InvalidBlockException


class BlockCrypto(private val url: String): Block,Filtrable {
    init{
        val connector = generateConnector(url)

    }


    /** generateConnector(url) returns an object that represents the connector for the desidered feed for Crypto
     * @param url is the url for the feedRSS
     * @return ConnectorCrypto for the url(if the URL is valid)
     */
    private fun generateConnector(url: String): Connector {

        val toReturn=ConnectorCrypto(url= url)
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
        return  ApplicationContextProvider.context!!.resources!!.getString(R.string.BlockCrypto)
    }

    fun url()= url

}