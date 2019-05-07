package com.megalexa.block.connector


import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.megalexa.models.connectors.ConnectorSport

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ConnectorSportValidityTest : ConnectorValidityTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.megalexa", appContext.packageName)
    }

    @Test
    override fun validConnector() {
        val someaccount = "https://www.goal.com/feeds/en/news"
        val accountIsValid = ConnectorSport(someaccount).valid()
        assertEquals(true, accountIsValid)
    }

    @Test
    override fun nonValidConnector() {
        val someaccount = "www.google.com"
        val accountIsValid = ConnectorSport(someaccount).valid()
        assertEquals(false, accountIsValid)
         }

    @Test
    fun valid() {
    }

}
