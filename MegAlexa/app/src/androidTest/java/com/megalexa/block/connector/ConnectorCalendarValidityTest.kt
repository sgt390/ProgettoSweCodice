package com.megalexa.block.connector


import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.megalexa.models.connectors.ConnectorCalendar
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ConnectorCalendarValidityTest : ConnectorValidityTest {

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.megalexa", appContext.packageName)
    }

    @Test
    override fun validConnector() {
        val someaccount = "someAccount@gmail.com"
        val secondArg = ""
        val accountIsValid = ConnectorCalendar(someaccount, secondArg).valid()
        assertEquals(true, accountIsValid)
    }

    @Test
    override fun nonValidConnector() {
        val someaccount = "someAccount@gmail.com"
        val secondArg = ""
        val account = ConnectorCalendar(someaccount, secondArg)
        assertNotEquals(account.valid(), "  something")
    }

}
