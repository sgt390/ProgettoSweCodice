package com.megalexa.service

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.megalexa.models.blocks.BlockTextToSpeech
import com.megalexa.util.service.BlockTextToSpeechService

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @Test
    override fun conversionFromObjectToJSon() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @Test
    fun validToJSON() {
        val expected = BlockTextToSpeech("This is the first block")
        val json = BlockTextToSpeechService.convertToJSON(expected)
        assertEquals(json.toString(),"{\"blockType\":\"TextToSpeech\",\"config\":{\"TextToSpeech\":\"This is the first block\"}}")
    }

    @Test
    fun validFromJSON() {
        val expected = BlockTextToSpeech("This is the first block")
        val json = BlockTextToSpeechService.convertToJSON(expected)
        val block = BlockTextToSpeechService.convertFromJSON(json)
        assertEquals(block.textBox(),"This is the first block")
    }
}
