package com.megalexa

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.megalexa.models.blocks.BlockFeedRss
import com.megalexa.models.blocks.BlockTextToSpeech
import com.megalexa.models.blocks.Filter
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
class BlockFilterTest : BlockTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.megalexa", appContext.packageName)
    }

    @Test
    override fun validBlock() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @Test
    override fun invalidBLock() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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
    fun validToJSON() {
        val expected = Filter(5)
        val json = FilterService.convertToJSON(expected)
        assertEquals(json.toString(), "{\"blockType\":\"Filter\",\"config\":{\"limit\":5}}")
    }

    @Test
    fun validFromJSON(){
        val expected = Filter(5)
        val json = FilterService.convertToJSON(expected)
        val block = FilterService.convertFromJSON(json)
        val num: Short = 5
        assertTrue(block.limit() == num)
    }

}