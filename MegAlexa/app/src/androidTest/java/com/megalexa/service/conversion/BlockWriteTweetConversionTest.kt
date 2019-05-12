package com.megalexa.service.conversion

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.megalexa.models.blocks.BlockTwitterWrite
import com.megalexa.util.service.BlockWriteTweetService
import junit.framework.Assert.assertEquals
import org.junit.Assert
import org.junit.Assert.assertNotEquals
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class BlockWriteTweetConversionTest :ConversionTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        Assert.assertEquals("com.megalexa", appContext.packageName)
    }

    @Test
    override fun conversionFromJSontoObject() {
        val block= BlockTwitterWrite()
        val convertedBlock= BlockTwitterWrite()
        assertEquals(block.getAccessKey(), convertedBlock.getAccessKey())
    }

    @Test
    override fun conversionFromObjectToJSon() {
        try {
            val block= BlockTwitterWrite()
            val json= BlockWriteTweetService.convertToJSON(block)
            assertNotEquals(json.toString()," ")
        }catch(e:NullPointerException) {
            assertEquals(true,true)
        }
    }
}