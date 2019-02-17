package com.megalexa.models.blocks

import com.megalexa.adapters.connectors.Connector
import com.megalexa.adapters.connectors.ConnectorFeedRss

/*
* may change uri with a more specific one
* */
class BlockFeedRss(val url: String): Block,Filtrable {
    init{
        val connector = generateConnector(url)

    }

    override var itemsToShow: Int
        get() = itemsToShow
        set(value) {
            itemsToShow=value
        }

    override var filtered: Boolean
        get() = filtered
        set(value) {
            filtered=value
        }


    private fun generateConnector(url: String): Connector {

        val toReturn=ConnectorFeedRss(url= url)

        if(toReturn.valid().equals(false)){
            //throw error
        }


        return toReturn


    }

    override fun getInformation() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }




}