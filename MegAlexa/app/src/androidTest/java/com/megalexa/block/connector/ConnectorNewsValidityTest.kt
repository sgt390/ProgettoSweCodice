package com.megalexa.block.connector


import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.megalexa.models.connectors.ConnectorNews

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ConnectorNewsValidityTest: ConnectorValidityTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.megalexa", appContext.packageName)
    }

    @Test
    override fun validConnector() {
        val someaccount = "http://feeds.skynews.com/feeds/rss/world.xml"
        val accountIsValid = ConnectorNews(someaccount).valid()
        assertEquals(true, accountIsValid)
    }

    @Test
    override fun nonValidConnector() {
        val someaccount = "someAccount@gmail.com"
        val accountIsValid = ConnectorNews(someaccount).valid()
        assertEquals(false, accountIsValid)
    }
}
