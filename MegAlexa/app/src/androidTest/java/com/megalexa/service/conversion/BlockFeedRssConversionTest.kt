package com.megalexa.service.conversion

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.megalexa.models.blocks.BlockFeedRss
import com.megalexa.util.service.BlockFeedRssService

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
class BlockFeedRssConversionTest : ConversionTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.megalexa", appContext.packageName)
    }

    @Test
    override fun conversionFromJSontoObject() {
        val expected = BlockFeedRss("http://feeds.reuters.com/Reuters/PoliticsNews")
        val json = BlockFeedRssService.convertToJSON(expected)
        val block = BlockFeedRssService.convertFromJSON(json)
        assertEquals(block.url(),"http://feeds.reuters.com/Reuters/PoliticsNews")
    }

    @Test
    override fun conversionFromObjectToJSon() {
        val expected = BlockFeedRss("http://feeds.reuters.com/Reuters/PoliticsNews")
        val json = BlockFeedRssService.convertToJSON(expected)
        val url = json.getJSONObject("config").getString("URL")
        val config = "{\"blockType\":\"FeedRSS\",\"config\":{\"URL\":\"\"}}"
        json.getJSONObject("config").put("URL","")

        assertTrue(url.equals("http://feeds.reuters.com/Reuters/PoliticsNews")
                && json.toString().equals(config))
    }

}
