package com.megalexa.service

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.megalexa.models.blocks.BlockBorsa
import com.megalexa.util.service.BlockBorsaService


import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
class BlockBorsaConversionTest : ConversionTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.megalexa", appContext.packageName)
    }

    @Test
    override fun conversionFromJSontoObject() {
        val expected = BlockBorsa("https://www.goal.com/feeds/en/news")
        val json = BlockBorsaService.convertToJSON(expected)
        val block = BlockBorsaService.convertFromJSON(json)
        assertEquals(block.url(),"https://www.goal.com/feeds/en/news")
    }

    @Test
    override fun conversionFromObjectToJSon() {
        val expected = BlockBorsa("https://www.goal.com/feeds/en/news")
        val json = BlockBorsaService.convertToJSON(expected)
        val url = json.getJSONObject("config").getString("URL")
        val config = "{\"blockType\":\"Borsa\",\"config\":{\"URL\":\"\"}}"
        json.getJSONObject("config").put("URL","")

        assertTrue(url.equals("https://www.goal.com/feeds/en/news")
                && json.toString().equals(config))
    }

}
