package com.megalexa

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.megalexa.models.blocks.BlockBorsa
import com.megalexa.util.service.BlockBorsaService


import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
class BlockBorsaServiceTest :ServiceTest {
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
        val expected = BlockBorsa("https://www.goal.com/feeds/en/news")
        val json = BlockBorsaService.convertToJSON(expected)
        val url = json.getJSONObject("config").getString("URL")
        val config = "{\"blockType\":\"Borsa\",\"config\":{\"URL\":\"\"}}"
        json.getJSONObject("config").put("URL","")

        assertTrue(url.equals("https://www.goal.com/feeds/en/news")
                && json.toString().equals(config))
    }

    @Test
    fun validFromJSON() {
        val expected = BlockBorsa("https://www.goal.com/feeds/en/news")
        val json = BlockBorsaService.convertToJSON(expected)
        val block = BlockBorsaService.convertFromJSON(json)
        assertEquals(block.url(),"https://www.goal.com/feeds/en/news")
    }

}
