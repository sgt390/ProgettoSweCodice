package com.megalexa

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.megalexa.models.blocks.BlockBorsa
import com.megalexa.models.blocks.BlockFeedRss
import com.megalexa.util.service.BlockBorsaService
import com.megalexa.util.service.BlockPinService
import com.megalexa.util.service.WorkflowService
import org.json.JSONArray
import org.json.JSONObject

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
class BlockBorsaServiceTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.megalexa", appContext.packageName)
    }

    @Test
    fun valid() {
        val expected = BlockBorsa("https://www.goal.com/feeds/en/news")
        val json = BlockBorsaService.convertToJSON(expected)
        assertEquals(json.toString(),"{\n" +
                "        \"blockType\": \"Borsa\",\n" +
                "        \"config\": {\n" +
                "          \"URL\": \"https://www.goal.com/feeds/en/news\"\n" +
                "        }\n" +
                "      }")
    }

}
