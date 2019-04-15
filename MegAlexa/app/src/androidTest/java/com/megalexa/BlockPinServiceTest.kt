package com.megalexa

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.megalexa.models.blocks.BlockPin
import com.megalexa.util.service.BlockPinService
import com.megalexa.util.service.WorkflowService
import org.json.JSONArray
import org.json.JSONObject

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
class BlockPinServiceTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.megalexa", appContext.packageName)
    }

    @Test
    fun validToJSON() {
        val expected = BlockPin(1234)
        val json = BlockPinService.convertToJSON(expected)
        assertEquals(json.toString(),"{\"blockType\":\"PIN\",\"config\":{\"PIN\":\"1234\"}}")
    }

    @Test
    fun validFromJSON() {
        val expected = BlockPin(1234)
        val json = BlockPinService.convertToJSON(expected)
        val block = BlockPinService.convertFromJSON(json)
        assertTrue(block.pin() == 1234)
    }

}
