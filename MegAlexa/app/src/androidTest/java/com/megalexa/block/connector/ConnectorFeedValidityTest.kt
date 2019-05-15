package com.megalexa.block.connector

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.megalexa.models.connectors.ConnectorFeedRss
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ConnectorFeedValidityTest : ConnectorValidityTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.megalexa", appContext.packageName)
    }

    @Test
    override fun nonValidConnector() {
        val uri = "www.google.com"
        val uriIsValid = ConnectorFeedRss(uri).valid()
        assertEquals(false, uriIsValid)
            }

    @Test
    override fun validConnector() {
        val uri = "https://feedforall.com/sample.xml"
        val uriIsValid = ConnectorFeedRss(uri).valid()
        assertEquals(true, uriIsValid)
    }

}