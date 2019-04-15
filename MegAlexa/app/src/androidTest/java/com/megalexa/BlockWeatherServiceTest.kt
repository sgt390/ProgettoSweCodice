package com.megalexa

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.megalexa.util.service.BlockWeatherService

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
class BlockWeatherServiceTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.megalexa", appContext.packageName)
    }

    @Test
    fun validToJSON() { TODO() }

    @Test
    fun validFromJSON() { TODO() }

    /*
    @Test
    fun validToJSON() {
        val expected = BlockWeather("4b1ea0b33edc40ba538b366b98484801","45.4064","11.8768")
        val json = BlockWeatherService.convertToJSON(expected)
        val config = "{\"blockType\":\"Weather\",\"config\":{\"APIKey\":\"4b1ea0b33edc40ba538b366b98484801\",
            \"Latitude\":\"45.4064\",\"Longitude\":\"11.8768\"}}"

        assertEquals(json.toString(),config)
    }
    */

    /*
    @Test
    fun validFromJSON() {
        val expected = BlockWeather("4b1ea0b33edc40ba538b366b98484801","45.4064","11.8768")
        val json = BlockWeatherService.convertToJSON(expected)
        val block = BlockWeatherService.convertFromJSON(json)

        assertTrue(block.APIKey().equals("4b1ea0b33edc40ba538b366b98484801") &&
                    block.getLatitude().equals("45.4064") &&
                    block.getLongitude().equals("11.8768"))
    }
    */
}