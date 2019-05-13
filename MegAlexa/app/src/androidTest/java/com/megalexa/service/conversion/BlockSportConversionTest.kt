package com.megalexa.service.conversion

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.megalexa.models.blocks.BlockSport
import com.megalexa.util.service.BlockSportService
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BlockSportConversionTest: ConversionTest {

    val feed_url="https://www.goal.com/feeds/en/news"

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.megalexa", appContext.packageName)
    }


    @Test
    override fun conversionFromJSontoObject() {
        val expected = BlockSport(feed_url)
        val json = BlockSportService.convertToJSON(expected)
        val block = BlockSportService.convertFromJSON(json)
        assertEquals(block.url(),feed_url)
    }

    @Test
    override fun conversionFromObjectToJSon() {
        val expected = BlockSport(feed_url)
        val json = BlockSportService.convertToJSON(expected)
        val url = json.getJSONObject("config").getString("URL")
        val config = "{\"blockType\":\"Sport\",\"config\":{\"URL\":\"\"}}"
        json.getJSONObject("config").put("URL","")

        assertTrue(url.equals(feed_url)
                && json.toString().equals(config))
    }

}
