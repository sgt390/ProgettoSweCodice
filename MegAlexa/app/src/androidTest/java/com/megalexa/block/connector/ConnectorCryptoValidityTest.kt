package com.megalexa.block.connector

import com.megalexa.models.connectors.ConnectorCrypto
import org.junit.Assert
import org.junit.Test


class ConnectorCryptoValidityTest :ConnectorValidityTest {

    @Test
    override fun validConnector() {
        val someaccount = "https://cryptocontrol.io/feed"
        val accountIsValid = ConnectorCrypto(someaccount).valid()
        Assert.assertEquals(true, accountIsValid)
         }

    @Test
    override fun nonValidConnector() {
        val someaccount = "www.google.com"
        val accountIsValid = ConnectorCrypto(someaccount).valid()
        Assert.assertEquals(false, accountIsValid) }

}