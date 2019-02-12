package com.megalexa.models.blocks

import com.megalexa.adapters.connectors.Connector

/*
* may change uri time with a more specific one
* */
class BlockFeedRss(val uri: String): Block,Filtrable {
    init{
        val connector: Connector = generateConnector(uri)
    }

    private fun generateConnector(uri: String): Connector {
        TODO("not implemented")
    }

    override fun getInformation() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}