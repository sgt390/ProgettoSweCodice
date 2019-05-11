package com.megalexa.block

import android.support.test.runner.AndroidJUnit4
import com.megalexa.models.blocks.BlockTwitter
import org.junit.Assert
import org.junit.Assert.assertNotEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BlockTwitterTest: BlockTest {

    @Test
    override fun validBlock() {
        val validScreenName = "POTUS"
        val block = BlockTwitter(validScreenName)
        junit.framework.Assert.assertEquals(block.getScreenName(), validScreenName)
    }

    @Test
    override fun invalidBLock() {
        val validScreenName = "notPOTUS"
        val block = BlockTwitter(validScreenName)
        assertNotEquals(block.getScreenName(), "POTUS")}

    @Test
    fun getAccessKey() {
        val validScreenName = "POTUS"
        val block = BlockTwitter(validScreenName)
        Assert.assertNotNull(block.getAccessKey())
    }

    @Test
    fun getAccessSecret() {
        val validScreenName = "POTUS"
        val block = BlockTwitter(validScreenName)
        Assert.assertNotNull(block.getAccessSecret())
    }

    @Test
    fun getConsumerKey() {
        val validScreenName = "POTUS"
        val block = BlockTwitter(validScreenName)
        Assert.assertNotNull(block.getConsumerKey())
    }

    @Test
    fun getConsumerSecret() {
        val validScreenName = "POTUS"
        val block = BlockTwitter(validScreenName)
        Assert.assertNotNull(block.getConsumerSecret())
    }
}