package com.megalexa.service

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.megalexa.models.blocks.BlockNews
import com.megalexa.util.service.BlockNewsService

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
class BlockNewsConversionTest : ConversionTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.megalexa", appContext.packageName)
    }

    @Test
    override fun conversionFromObjectToJSon() {
        val expected = BlockNews("https://news.google.com/rss?hl=it&gl=IT&ceid=IT:it")
        val json = BlockNewsService.convertToJSON(expected)
        val url = json.getJSONObject("config").getString("URL")
        val config = "{\"blockType\":\"News\",\"config\":{\"URL\":\"\"}}"
        json.getJSONObject("config").put("URL","")

        assertTrue(url.equals("https://news.google.com/rss?hl=it&gl=IT&ceid=IT:it")
                && json.toString().equals(config))
    }

    @Test
    override fun conversionFromJSontoObject() {
        val expected = BlockNews("https://news.google.com/rss?hl=it&gl=IT&ceid=IT:it")
        val json = BlockNewsService.convertToJSON(expected)
        val block = BlockNewsService.convertFromJSON(json)
        assertEquals(block.url(),"https://news.google.com/rss?hl=it&gl=IT&ceid=IT:it")
    }
}