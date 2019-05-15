/*
 *
 *  File name. BlockBorsaCoversionTest.kt
 *  Version: 1.0.0
 *  Date: 2019-04-15
 *  Author: Ludovico Brocca
 *  License:
 *  History:
 *  Author            || Date            || Description
 *  Ludovico Brocca   || 2019-04-15      || File created
 *  Gian Marco Bratzu || 2019-04-16     || Verifying code
 *
 */
package com.megalexa.service.conversion


import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.megalexa.models.blocks.BlockBorsa
import com.megalexa.util.service.BlockBorsaService
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BlockBorsaConversionTest : ConversionTest {

    val url="https://www.goal.com/feeds/en/news"

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.megalexa", appContext.packageName)
    }

    @Test
    override fun conversionFromJSontoObject() {
        val expected = BlockBorsa(url)
        val json = BlockBorsaService.convertToJSON(expected)
        val block = BlockBorsaService.convertFromJSON(json)
        assertEquals(block.url(),url)
    }

    @Test
    override fun conversionFromObjectToJSon() {
        val expected = BlockBorsa(url)
        val json = BlockBorsaService.convertToJSON(expected)
        val url = json.getJSONObject("config").getString("URL")
        val config = "{\"blockType\":\"Borsa\",\"config\":{\"URL\":\"\"}}"
        json.getJSONObject("config").put("URL","")

        assertTrue(url.equals(url)
                && json.toString().equals(config))
    }

}
