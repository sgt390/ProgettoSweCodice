package com.megalexa.block.connector


import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.megalexa.models.connectors.ConnectorWeather

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ConnectorWeatherValidityTest: ConnectorValidityTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.megalexa", appContext.packageName)
    }

    @Test
    override fun validConnector() {
        val city = "Padova"
        val cityIsValid = ConnectorWeather(city).valid()
        assertEquals(true, cityIsValid)
    }

    @Test
    override fun nonValidConnector() {
        val city = "dsaijdasidnaiuda"
        val cityIsValid = ConnectorWeather(city).valid()
        assertEquals(false, cityIsValid)
            }
}
