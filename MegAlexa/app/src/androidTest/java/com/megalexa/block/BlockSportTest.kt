/*
 *
 *  File name. BlockPinTest.kt
 *  Version: 1.0.0
 *  Date: 2019-04-14
 *  Author: Ludovico Brocca
 *  License:
 *  History:
 *  Author            || Date            || Description
 *  Ludovico Brocca   || 2019-04-14      || File created
 *  Gian Marco Bratzu || 2019-04-15      || Verifying code
 *
 */
package com.megalexa.block

import android.support.test.runner.AndroidJUnit4
import com.megalexa.models.blocks.BlockSport
import com.megalexa.util.InvalidBlockException
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BlockSportTest: BlockTest {

    @Test
    override fun invalidBLock() {
        var expected= "no errors found"
        try {
            val block= BlockSport("https://google.com")
        }catch (e: InvalidBlockException) {
            expected= e.getErrorMessage()
        }

        assertEquals(expected, "Invalid Block")
    }

    @Test
    override fun validBlock() {
        val validUrl= "https://www.atptour.com/en/media/rss-feed/xml-feed"
        val block= BlockSport(validUrl)
        assertEquals(block.url(), validUrl)
    }
}