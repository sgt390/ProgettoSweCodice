/*
 *
 *  File name. BlockPinTest.kt
 *  Version: 1.0.0
 *  Date: 2019-04-13
 *  Author: Ludovico Brocca
 *  License:
 *  History:
 *  Author            || Date            || Description
 *  Ludovico Brocca   || 2019-04-13      || File created
 *  Gian Marco Bratzu || 2019-04-14      || Verifying code
 *
 */
package com.megalexa.block

import android.support.test.runner.AndroidJUnit4
import com.megalexa.models.blocks.BlockReadEmail
import org.junit.Assert.assertNotEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BlockReadEmailTest:BlockTest {

    @Test
    override fun validBlock() {
        val block= BlockReadEmail("sdgsjdgfjhds","sdfksjdhfkhsd")
        assertNotEquals(block.getClientId(),"something")
    }

    @Test
    override fun invalidBLock() {
        val block= BlockReadEmail("sdgsjdgfjhds","sdfksjdhfkhsd")
        assertNotEquals(block.getClientId(),"something")
    }
}