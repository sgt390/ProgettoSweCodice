/*
 *
 *  File name. BlockCryptoCoversionTest.kt
 *  Version: 1.0.0
 *  Date: 2019-04-16
 *  Author: Ludovico Brocca
 *  License:
 *  History:
 *  Author            || Date            || Description
 *  Ludovico Brocca   || 2019-04-16      || File created
 *  Gian Marco Bratzu || 2019-04-17     || Verifying code
 *
 */
package com.megalexa.service.conversion

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.megalexa.models.blocks.BlockCrypto
import com.megalexa.util.service.BlockCryptoService
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BlockCryptoConversionTest : ConversionTest {

    val feed_url="http://feeds.reuters.com/Reuters/PoliticsNews"

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.megalexa", appContext.packageName)
    }

    @Test
    override fun conversionFromJSontoObject() {
        val expected = BlockCrypto(feed_url)
        val json = BlockCryptoService.convertToJSON(expected)
        val block = BlockCryptoService.convertFromJSON(json)
        assertEquals(block.url(),feed_url)
    }

    @Test
    override fun conversionFromObjectToJSon() {
        val expected = BlockCrypto(feed_url)
        val json = BlockCryptoService.convertToJSON(expected)
        val url = json.getJSONObject("config").getString("URL")
        val config = "{\"blockType\":\"Crypto\",\"config\":{\"URL\":\"\"}}"
        json.getJSONObject("config").put("URL","")

        assertTrue(url.equals(feed_url)
                && json.toString().equals(config))
    }

}
