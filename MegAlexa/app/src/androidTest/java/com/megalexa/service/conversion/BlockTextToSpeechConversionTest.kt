/*
 *
 *  File name. BlockTextToSpeechConversionTest.kt
 *  Version: 1.0.0
 *  Date: 2019-04-20
 *  Author: Ludovico Brocca
 *  License:
 *  History:
 *  Author            || Date            || Description
 *  Ludovico Brocca   || 2019-04-20      || File created
 *  Gian Marco Bratzu || 2019-04-21     || Verifying code
 *
 */




package com.megalexa.service.conversion
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.megalexa.models.blocks.BlockTextToSpeech
import com.megalexa.util.service.BlockTextToSpeechService
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BlockTextToSpeechConversionTest : ConversionTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.megalexa", appContext.packageName)
    }

    @Test
    override fun conversionFromJSontoObject() {
        val param="This is the first block"
        val expected = BlockTextToSpeech(param)
        val json = BlockTextToSpeechService.convertToJSON(expected)
        val block = BlockTextToSpeechService.convertFromJSON(json)
        assertEquals(block.textBox(),param)
    }

    @Test
    override fun conversionFromObjectToJSon() {
        val expected = BlockTextToSpeech("This is the first block")
        val json = BlockTextToSpeechService.convertToJSON(expected)
        assertEquals(json.toString(),"{\"blockType\":\"TextToSpeech\",\"config\":{\"TextToSpeech\":\"This is the first block\"}}")
    }
}
