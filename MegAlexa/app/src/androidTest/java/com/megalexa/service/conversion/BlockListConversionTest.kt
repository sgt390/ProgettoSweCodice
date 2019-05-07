package com.megalexa.service.conversion

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.megalexa.models.blocks.BlockList
import com.megalexa.util.service.BlockListService
import org.json.JSONArray
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BlockListConversionTest:ConversionTest {

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        Assert.assertEquals("com.megalexa", appContext.packageName)
    }

    @Test
    override fun conversionFromJSontoObject() {
        val expected = BlockList(JSONArray())
        val json = BlockListService.convertToJSON(expected)
        val block = BlockListService.convertFromJSON(json)
        Assert.assertEquals(block.getList(), JSONArray())
    }

    @Test
    override fun conversionFromObjectToJSon() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}