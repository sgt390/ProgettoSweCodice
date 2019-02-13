package com.megalexa.adapters.connectors

class ConnectorFeedRss(private val uri: String):Connector {
    init {
        val feedMessage:String = connect(uri)
    }

    override fun connect(uri: String):String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * A ConnectorFeedRss is valid if the feedMessage is correct
     * @return feed is valid
     */
    override fun valid():Boolean {
        TODO(reason = "not implemented")
    }

}