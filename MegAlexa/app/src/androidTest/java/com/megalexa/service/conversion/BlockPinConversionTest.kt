package com.megalexa.service.conversion

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.megalexa.models.blocks.BlockPin
import com.megalexa.util.service.BlockPinService

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
class BlockPinConversionTest: ConversionTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.megalexa", appContext.packageName)
    }

    @Test
    override fun conversionFromJSontoObject() {
        val expected = BlockPin(1234)
        val json = BlockPinService.convertToJSON(expected)
        assertEquals(json.toString(),"{\"blockType\":\"PIN\",\"config\":{\"PIN\":\"1234\"}}")
           }

    @Test
    override fun conversionFromObjectToJSon() {
        val expected = BlockPin(1234)
        val json = BlockPinService.convertToJSON(expected)
        val block = BlockPinService.convertFromJSON(json)
        assertTrue(block.pin() == 1234)
    }


}
