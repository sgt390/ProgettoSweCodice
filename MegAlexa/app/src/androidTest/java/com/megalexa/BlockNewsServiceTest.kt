package com.megalexa

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.megalexa.models.blocks.BlockNews
import com.megalexa.util.service.BlockNewsService

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
class BlockNewsServiceTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.megalexa", appContext.packageName)
    }

    @Test
    fun valid() {
        val expected = BlockNews("https://news.google.com/rss?hl=it&gl=IT&ceid=IT:it")
        val json = BlockNewsService.convertToJSON(expected)
        assertEquals(json.toString(),
            "{\"blockType\":\"News\",\"config\":{\"URL\":\"https://news.google.com/rss?hl=it&gl=IT&ceid=IT:it\"}}")
    }

}