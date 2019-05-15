/*
 *
 *  File name. BlockNewsCoversionTest.kt
 *  Version: 1.0.0
 *  Date: 2019-04-18
 *  Author: Ludovico Brocca
 *  License:
 *  History:
 *  Author            || Date            || Description
 *  Ludovico Brocca   || 2019-04-18      || File created
 *  Gian Marco Bratzu || 2019-04-19     || Verifying code
 *
 */
package com.megalexa.service.conversion

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.megalexa.models.blocks.BlockNews
import com.megalexa.util.service.BlockNewsService
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BlockNewsConversionTest : ConversionTest {

    val feed_url="https://news.google.com/rss?hl=it&gl=IT&ceid=IT:it"

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.megalexa", appContext.packageName)
    }

    @Test
    override fun conversionFromObjectToJSon() {
        val expected = BlockNews(feed_url)
        val json = BlockNewsService.convertToJSON(expected)
        val url = json.getJSONObject("config").getString("URL")
        val config = "{\"blockType\":\"News\",\"config\":{\"URL\":\"\"}}"
        json.getJSONObject("config").put("URL","")

        assertTrue(url.equals(feed_url)
                && json.toString().equals(config))
    }

    @Test
    override fun conversionFromJSontoObject() {
        val expected = BlockNews(feed_url)
        val json = BlockNewsService.convertToJSON(expected)
        val block = BlockNewsService.convertFromJSON(json)
        assertEquals(block.url(),feed_url)
    }
}