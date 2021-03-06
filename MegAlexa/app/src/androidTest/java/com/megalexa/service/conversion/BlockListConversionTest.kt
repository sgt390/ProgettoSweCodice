/*
 *
 *  File name. BlockListCoversionTest.kt
 *  Version: 1.0.0
 *  Date: 2019-04-17
 *  Author: Ludovico Brocca
 *  License:
 *  History:
 *  Author            || Date            || Description
 *  Ludovico Brocca   || 2019-04-17      || File created
 *  Gian Marco Bratzu || 2019-04-18     || Verifying code
 *
 */
package com.megalexa.service.conversion

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.megalexa.models.blocks.BlockList
import com.megalexa.util.service.BlockListService
import junit.framework.Assert.assertEquals
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
        assertEquals(block.getList(), JSONArray())
    }

    @Test
    override fun conversionFromObjectToJSon() {
        val block= BlockList(JSONArray())
        val json = BlockListService.convertToJSON(block)
        assertEquals(json.toString(),"{\"blockType\":\"List\",\"config\":{\"List\":[]}}")

    }
}