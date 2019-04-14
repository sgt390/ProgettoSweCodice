package com.megalexa

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.megalexa.models.blocks.BlockCrypto
import com.megalexa.models.blocks.BlockFeedRss
import com.megalexa.util.service.BlockCryptoService
import com.megalexa.util.service.BlockPinService
import com.megalexa.util.service.WorkflowService
import org.json.JSONArray
import org.json.JSONObject

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
class BlockCryptoServiceTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.megalexa", appContext.packageName)
    }

    @Test
    fun valid() {
        val expected = BlockCrypto("http://feeds.reuters.com/Reuters/PoliticsNews")
        val json = BlockCryptoService.convertToJSON(expected)
        assertEquals(json.toString(),
            "{\"blockType\":\"Crypto\",\"config\":{\"URL\":\"http://feeds.reuters.com/Reuters/PoliticsNews\"}}")
    }

}
