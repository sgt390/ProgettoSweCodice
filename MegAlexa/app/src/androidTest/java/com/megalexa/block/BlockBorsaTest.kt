/*
 *
 *  File name. BlockBorsaTest.kt
 *  Version: 1.0.0
 *  Date: 2019-04-10
 *  Author: Ludovico Brocca
 *  License:
 *  History:
 *  Author            || Date            || Description
 *  Ludovico Brocca   || 2019-04-10      || File created
 *  Gian Marco Bratzu || 2019-04-11      || Verifying code
 *
 */

package com.megalexa.block

import android.support.test.runner.AndroidJUnit4
import com.megalexa.models.blocks.BlockBorsa
import com.megalexa.util.InvalidBlockException
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BlockBorsaTest:BlockTest {

    @Test
    override fun invalidBLock() {
        var expected= "no errors found"
        try {
            val block= BlockBorsa("https://google.com")
        }catch (e: InvalidBlockException) {
            expected= e.getErrorMessage()
        }

        assertEquals(expected,"Invalid Block")
    }

    @Test
    override fun validBlock() {

        val validUrl= "https://www.cnbc.com/id/20910258/device/rss/rss.html"
        val block= BlockBorsa(validUrl)
        assertEquals(block.url(),validUrl)
    }
}