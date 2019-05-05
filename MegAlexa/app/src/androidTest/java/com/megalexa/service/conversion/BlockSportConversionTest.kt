package com.megalexa.service.conversion

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.megalexa.models.blocks.BlockSport
import com.megalexa.util.service.BlockSportService

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
class BlockSportConversionTest: ConversionTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.megalexa", appContext.packageName)
    }


    @Test
    override fun conversionFromJSontoObject() {
        val expected = BlockSport("https://www.goal.com/feeds/en/news")
        val json = BlockSportService.convertToJSON(expected)
        val block = BlockSportService.convertFromJSON(json)
        assertEquals(block.url(),"https://www.goal.com/feeds/en/news")
    }

    @Test
    override fun conversionFromObjectToJSon() {
        val expected = BlockSport("https://www.goal.com/feeds/en/news")
        val json = BlockSportService.convertToJSON(expected)
        val url = json.getJSONObject("config").getString("URL")
        val config = "{\"blockType\":\"Sport\",\"config\":{\"URL\":\"\"}}"
        json.getJSONObject("config").put("URL","")

        assertTrue(url.equals("https://www.goal.com/feeds/en/news")
                && json.toString().equals(config))
    }

}
