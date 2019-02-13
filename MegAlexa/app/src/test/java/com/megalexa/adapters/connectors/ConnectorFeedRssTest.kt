package com.megalexa.adapters.connectors

import org.junit.Test

import org.junit.Assert.*

class ConnectorFeedRssTest {

    @Test
    fun valid() {
        TODO("test the connection with a correct Feed RSS uri")
        val uri = ""
        val uriIsValid = ConnectorFeedRss(uri).valid()
        assertEquals("ConnectorFeedRss connect test", uriIsValid, true)
    }

}