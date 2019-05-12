package com.megalexa.service.conversion

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.megalexa.models.blocks.Filter
import com.megalexa.util.service.FilterService
import junit.framework.Assert.assertEquals
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FilterConversionTest:ConversionTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        Assert.assertEquals("com.megalexa", appContext.packageName)
    }

    @Test
    override fun conversionFromJSontoObject() {
        val filter= Filter(5)
        val convertedFilter= FilterService.convertFromJSON(FilterService.convertToJSON(filter))
        assertEquals(filter.getResult(),convertedFilter.getResult())
    }

    @Test
    override fun conversionFromObjectToJSon() {
        val filter= Filter(5)
        val json= FilterService.convertToJSON(filter)
        assertEquals(json.toString(),"{\"blockType\":\"Filter\",\"config\":{\"limit\":5}}")
    }
}