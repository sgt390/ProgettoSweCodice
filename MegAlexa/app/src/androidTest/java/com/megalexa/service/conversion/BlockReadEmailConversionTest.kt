/*
 *
 *  File name. BlockRadEmailConversionTest.kt
 *  Version: 1.0.0
 *  Date: 2019-04-19
 *  Author: Ludovico Brocca
 *  License:
 *  History:
 *  Author            || Date            || Description
 *  Ludovico Brocca   || 2019-04-19      || File created
 *  Gian Marco Bratzu || 2019-04-20     || Verifying code
 *
 */
package com.megalexa.service.conversion

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.megalexa.models.blocks.BlockReadEmail
import com.megalexa.util.service.BlockReadEmailService
import junit.framework.Assert.assertEquals
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
        val block= BlockReadEmail("token","refresh")
        val convertedBlock= BlockReadEmailService.convertFromJSON(BlockReadEmailService.convertToJSON(block))
        assertEquals(block.getToken(),convertedBlock.getToken())
    }

    @Test
    override fun conversionFromObjectToJSon() {
//       val expected = BlockReadEmail(JSONObject())
//        val json = BlockReadEmailService.convertToJSON(expected)
//        val tokenGmail = json.getJSONObject("config").getString("token")
//        val config = "{\"blockType\":\"News\",\"config\":{\"URL\":\"\"}}"
//        json.getJSONObject("config").put("Credential","Cred")
//        println("dovrebbe essere il token "+tokenGmail.toString())
//        assertTrue(tokenGmail.equals("") && json.toString().equals(config))
        }
}