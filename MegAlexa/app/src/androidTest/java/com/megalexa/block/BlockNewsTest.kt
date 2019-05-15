/*
 *
 *  File name. BlockNewsTest.kt
 *  Version: 1.0.0
 *  Date: 2019-04-12
 *  Author: Ludovico Brocca
 *  License:
 *  History:
 *  Author            || Date            || Description
 *  Ludovico Brocca   || 2019-04-12      || File created
 *  Gian Marco Bratzu || 2019-04-13      || Verifying code
 *
 */
package com.megalexa.block

import android.support.test.runner.AndroidJUnit4
import com.megalexa.models.blocks.BlockNews
import com.megalexa.util.InvalidBlockException
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BlockNewsTest : BlockTest {

    @Test
    override fun invalidBLock() {
        var expected= "no errors found"
        try {
            val block= BlockNews("https://google.com")
        }catch (e: InvalidBlockException) {
            expected= e.getErrorMessage()
        }

        assertEquals(expected, "Invalid Block")
    }

    @Test
    override fun validBlock() {
        val validUrl= "http://feeds.bbci.co.uk/news/video_and_audio/world/rss.xml"
        val block= BlockNews(validUrl)
        assertEquals(block.url(), validUrl)
    }
}