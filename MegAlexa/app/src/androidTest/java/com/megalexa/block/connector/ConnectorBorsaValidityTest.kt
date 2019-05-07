package com.megalexa.block.connector

import com.megalexa.models.connectors.ConnectorBorsa
import org.junit.Assert
import org.junit.Test

class ConnectorBorsaValidityTest :ConnectorValidityTest {

    @Test
    override fun validConnector() {
        val someaccount = "https://www.cnbc.com/id/20910258/device/rss/rss.html"
        val accountIsValid = ConnectorBorsa(someaccount).valid()
        Assert.assertEquals(true, accountIsValid)
         }

    @Test
    override fun nonValidConnector() {
        val someaccount = "https://www.cnbc.com"
        val accountIsValid = ConnectorBorsa(someaccount).valid()
        Assert.assertEquals(false, accountIsValid)
       }
}