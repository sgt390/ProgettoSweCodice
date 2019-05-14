/*
 *
 *  File name. BlockTwitterHomeTLTest.kt
 *  Version: 1.0.0
 *  Date: 2019-04-15
 *  Author: Ludovico Brocca
 *  License:
 *  History:
 *  Author            || Date            || Description
 *  Ludovico Brocca   || 2019-04-15      || File created
 *  Gian Marco Bratzu || 2019-04-16      || Verifying code
 *
 */
package com.megalexa.block

import android.support.test.runner.AndroidJUnit4
import com.megalexa.models.blocks.BlockTwitterHomeTL
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BlockTwitterHomeTLTest:BlockTest {

    @Test
    override fun validBlock() {
        val block = BlockTwitterHomeTL()
        Assert.assertEquals(block.getInformation(), "Twitter home block created")
    }

    @Test
    override fun invalidBLock() {
        val block = BlockTwitterHomeTL()
        Assert.assertNotEquals(block.getInformation(), "Twitter home block not Created")
    }

    @Test
    fun getAccessKey() {
        val block = BlockTwitterHomeTL()
        Assert.assertNotNull(block.getAccessKey())
    }

    @Test
    fun getAccessSecret() {
        val block = BlockTwitterHomeTL()
        Assert.assertNotNull(block.getAccessSecret())
    }

    @Test
    fun getConsumerKey() {
        val block = BlockTwitterHomeTL()
        Assert.assertNotNull(block.getConsumerKey())
    }

    @Test
    fun getConsumerSecret() {
        val block = BlockTwitterHomeTL()
        Assert.assertNotNull(block.getConsumerSecret())
    }
}