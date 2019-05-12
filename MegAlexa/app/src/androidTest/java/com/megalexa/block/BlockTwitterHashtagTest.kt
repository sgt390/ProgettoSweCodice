package com.megalexa.block

import android.support.test.runner.AndroidJUnit4
import com.megalexa.models.blocks.BlockTwitterHashtag
import junit.framework.Assert
import org.junit.Assert.assertNotEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BlockTwitterHashtagTest:BlockTest {

    @Test
    override fun invalidBLock() {
        val validScreenName = "#POTUS"
        val block = BlockTwitterHashtag(validScreenName)
        assertNotEquals(block.getHashtag(), "#notPOTUS")}

    @Test
    override fun validBlock() {
        val validScreenName = "#POTUS"
        val block = BlockTwitterHashtag(validScreenName)
        Assert.assertEquals(block.getHashtag(), validScreenName)
    }
    @Test
    fun getAccessKey() {
        val validScreenName = "#POTUS"
        val block = BlockTwitterHashtag(validScreenName)
        org.junit.Assert.assertNotNull(block.getAccessKey())
    }

    @Test
    fun getAccessSecret() {
        val validScreenName = "#POTUS"
        val block = BlockTwitterHashtag(validScreenName)
        org.junit.Assert.assertNotNull(block.getAccessSecret())
    }

    @Test
    fun getConsumerKey() {
        val validScreenName = "#POTUS"
        val block = BlockTwitterHashtag(validScreenName)
        org.junit.Assert.assertNotNull(block.getConsumerKey())
    }

    @Test
    fun getConsumerSecret() {
        val validScreenName = "#POTUS"
        val block = BlockTwitterHashtag(validScreenName)
        org.junit.Assert.assertNotNull(block.getConsumerSecret())
    }
}