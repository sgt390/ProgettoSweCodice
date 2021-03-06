/*
 *
 *  File name. BlockWeatherConversionTest.kt
 *  Version: 1.0.0
 *  Date: 2019-04-22
 *  Author: Ludovico Brocca
 *  License:
 *  History:
 *  Author            || Date            || Description
 *  Ludovico Brocca   || 2019-04-22      || File created
 *  Gian Marco Bratzu || 2019-04-23     || Verifying code
 *
 */
package com.megalexa.service.conversion

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.megalexa.models.blocks.BlockWeather
import com.megalexa.util.service.BlockWeatherService
import org.json.JSONObject
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BlockWeatherConversionTest: ConversionTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.megalexa", appContext.packageName)
    }

    @Test
    override fun conversionFromJSontoObject() {
        val jsonObj = JSONObject()
        jsonObj.put("APIKey","4b1ea0b33edc40ba538b366b98484801")
        jsonObj.put("Latitude","45.41")
        jsonObj.put("Longitude","11.87")
        val expected = BlockWeather(jsonObj)
        val json = BlockWeatherService.convertToJSON(expected)
        val block = BlockWeatherService.convertFromJSON(json)

        assertTrue(
            block.getAPIKey().equals("4b1ea0b33edc40ba538b366b98484801") &&
                    block.getLatitude().equals("45.41") &&
                    block.getLongitude().equals("11.87")
        )
        }

    @Test
    override fun conversionFromObjectToJSon() {
        val jsonObj = JSONObject()
        jsonObj.put("APIKey","4b1ea0b33edc40ba538b366b98484801")
        jsonObj.put("Latitude","45.41")
        jsonObj.put("Longitude","11.87")
        val expected = BlockWeather(jsonObj)
        val json = BlockWeatherService.convertToJSON(expected)
        assertEquals(json.toString(),"{\"blockType\":\"Weather\",\"config\":{\"APIKey\":\"" +
                "4b1ea0b33edc40ba538b366b98484801\",\"Latitude\":\"" +
                "45.41\",\"Longitude\":\"11.87\"}}")
    }

}

