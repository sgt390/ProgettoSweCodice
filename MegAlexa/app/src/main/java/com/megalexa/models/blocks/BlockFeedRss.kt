package com.megalexa.models.blocks

import com.megalexa.adapters.connectors.Connector
import com.megalexa.adapters.connectors.ConnectorFeedRss


class BlockFeedRss(val url: String): Block,Filtrable {
    init{
        val connector = generateConnector(url)

    }


    /** generateConnector(url) returns an object that represents the connector for the desidered feedRSS
     * @param url is the url for the feedRSS
     * @return ConnectorFeedRss for the url(if the URL is valid)
     */
    private fun generateConnector(url: String): Connector {

        val toReturn=ConnectorFeedRss(url= url)

        if(toReturn.valid().equals(false)){
            //throw InvalidBLockException() TODO("custom error handling required ")
        }

        return toReturn

    }

    /** getInformation()
     * @return  String that sums up the information for the given feedRSS
     *
     */
    override fun getInformation():String {
        return "Feed RSS block created for $url URL "
    }

}