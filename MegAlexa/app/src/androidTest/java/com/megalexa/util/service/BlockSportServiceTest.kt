package com.megalexa.util.service

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.megalexa.models.blocks.BlockPin
import com.megalexa.models.blocks.BlockSport
import com.megalexa.util.service.BlockPinService
import com.megalexa.util.service.BlockSportService
import com.megalexa.util.service.WorkflowService
import org.json.JSONArray
import org.json.JSONObject

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
class BlockSportServiceTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.megalexa", appContext.packageName)
    }

    @Test
    fun validToJSON() {
        val expected = BlockSport("https://www.goal.com/feeds/en/news")
        val json = BlockSportService.convertToJSON(expected)
        val url = json.getJSONObject("config").getString("URL")
        val config = "{\"blockType\":\"Sport\",\"config\":{\"URL\":\"\"}}"
        json.getJSONObject("config").put("URL","")

        assertTrue(url.equals("https://www.goal.com/feeds/en/news")
                && json.toString().equals(config))
    }

    @Test
    fun validFromJSON() {
        val expected = BlockSport("https://www.goal.com/feeds/en/news")
        val json = BlockSportService.convertToJSON(expected)
        val block = BlockSportService.convertFromJSON(json)
        assertEquals(block.url(),"https://www.goal.com/feeds/en/news")
    }
}
