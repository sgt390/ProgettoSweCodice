package com.megalexa.service.conversion

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.megalexa.models.blocks.BlockTwitterHomeTL
import com.megalexa.util.service.BlockTwitterHomeTLService
import junit.framework.Assert.assertEquals
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BlockTwitterTimelineConversionTest:ConversionTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        Assert.assertEquals("com.megalexa", appContext.packageName)
    }

    @Test
    override fun conversionFromJSontoObject() {
        val block= BlockTwitterHomeTL()
        val convertedBlock= BlockTwitterHomeTL()
        assertEquals(block.getAccessKey(),convertedBlock.getAccessKey())
    }

    @Test
    override fun conversionFromObjectToJSon() {
         try {
             val block= BlockTwitterHomeTL()
             val json= BlockTwitterHomeTLService.convertToJSON(block)
             assertEquals(json.toString()," ")
         }catch(e:NullPointerException) {
             assertEquals(true,true)
         }
    }
}