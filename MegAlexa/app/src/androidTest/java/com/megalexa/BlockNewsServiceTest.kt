package com.megalexa

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.megalexa.models.blocks.BlockBorsa
import com.megalexa.models.blocks.BlockFeedRss
import com.megalexa.models.blocks.BlockNews
import com.megalexa.util.service.BlockBorsaService
import com.megalexa.util.service.BlockNewsService
import com.megalexa.util.service.BlockPinService
import com.megalexa.util.service.WorkflowService
import org.json.JSONArray
import org.json.JSONObject

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
class BlockNewsServiceTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.megalexa", appContext.packageName)
    }

    @Test
    fun valid() {
        val expected = BlockNews("https://news.google.com/rss?hl=it&gl=IT&ceid=IT:it")
        val json = BlockNewsService.convertToJSON(expected)
        assertEquals(json.toString(),"{\n" +
                "        \"blockType\": \"News\",\n" +
                "        \"config\": {\n" +
                "          \"URL\": \"https://news.google.com/rss?hl=it&gl=IT&ceid=IT:it\"\n" +
                "        }\n" +
                "      }")
    }

}