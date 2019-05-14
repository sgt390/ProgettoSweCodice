/*
 *
 *  File name. BlockCryptoTest.kt
 *  Version: 1.0.0
 *  Date: 2019-04-11
 *  Author: Ludovico Brocca
 *  License:
 *  History:
 *  Author            || Date            || Description
 *  Ludovico Brocca   || 2019-04-11      || File created
 *  Gian Marco Bratzu || 2019-04-12      || Verifying code
 *
 */

package com.megalexa.block

import android.support.test.runner.AndroidJUnit4
import com.megalexa.models.blocks.BlockCrypto
import com.megalexa.util.InvalidBlockException
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BlockCryptoTest:BlockTest {

    @Test
    override fun invalidBLock() {
        var expected= "no errors found"
        try {
            val block= BlockCrypto("https://google.com")
        }catch (e: InvalidBlockException) {
            expected= e.getErrorMessage()
        }

        assertEquals(expected, "Invalid Block")
    }

    @Test
    override fun validBlock() {
        val validUrl= "https://www.coindesk.com/feed"
        val block= BlockCrypto(validUrl)
        assertEquals(block.url(),validUrl)
    }
}