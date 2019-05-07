package com.megalexa.service.conversion

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.support.v4.media.session.MediaSessionCompat
import com.megalexa.models.blocks.BlockReadEmail
import com.megalexa.util.service.BlockReadEmailService
import junit.framework.Assert.assertTrue
import org.json.JSONObject
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BlockReadEmailConversionTest:ConversionTest {

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        Assert.assertEquals("com.megalexa", appContext.packageName)
    }

    @Test
    override fun conversionFromJSontoObject() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @Test
    override fun conversionFromObjectToJSon() {
       val expected = BlockReadEmail(JSONObject())
        val json = BlockReadEmailService.convertToJSON(expected)
        val Token_Gmail = json.getJSONObject("config").getString("token")
        val config = "{\"blockType\":\"News\",\"config\":{\"URL\":\"\"}}"
        json.getJSONObject("config").put("Credential","Cred")
        println("dovrebbe essere il token "+Token_Gmail.toString())
        assertTrue(Token_Gmail.equals("") && json.toString().equals(config))    }
}