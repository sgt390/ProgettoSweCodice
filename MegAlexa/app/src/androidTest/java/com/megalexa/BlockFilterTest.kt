package com.megalexa

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.megalexa.models.blocks.BlockFeedRss
import com.megalexa.models.blocks.BlockTextToSpeech
import com.megalexa.models.blocks.Filter
import com.megalexa.models.connectors.ConnectorAmazonMusic
import com.megalexa.util.service.FilterService

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class BlockFilterTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.megalexa", appContext.packageName)
    }

    @Test
    fun withFilterableBlock() {
        val filter = Filter(5)

        val block= BlockFeedRss("https://feedforall.com/sample.xml")
        filter.attachTo(block)
        assertEquals(true,filter.getResult())
    }
    
    @Test
    fun withNonFilterableBlock() {
        val filter = Filter(5)
        val block= BlockTextToSpeech("Prova")
        filter.attachTo(block)
        assertEquals(false,filter.getResult())

    }

    @Test
    fun valid() {
        val expected = Filter(5)
        val json = FilterService.convertToJSON(expected)
        assertEquals(json.toString(), "{\"blockType\":\"Filter\",\"config\":{\"limit\":\"5\"}}")
    }

}