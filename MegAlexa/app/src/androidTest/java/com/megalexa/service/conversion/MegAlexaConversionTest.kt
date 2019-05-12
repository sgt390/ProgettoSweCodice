package com.megalexa.service.conversion

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.megalexa.models.MegAlexa
import com.megalexa.util.service.MegAlexaService
import junit.framework.Assert.assertEquals
import org.json.JSONObject
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MegAlexaConversionTest :ConversionTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        Assert.assertEquals("com.megalexa", appContext.packageName)
    }

    @Test
    override fun conversionFromJSontoObject() {
        val app= MegAlexa.build()
        val second= MegAlexa.getInstance()
        assertEquals(app.getUser().getID(),second.getUser().getID())
    }

    @Test
    override fun conversionFromObjectToJSon() {
        val app= MegAlexa.build()
        val json= MegAlexaService.convertToJSON(app)
        assertEquals(json.toString(),"{}")
    }
}