package com.megalexa.block

import android.support.test.runner.AndroidJUnit4
import android.util.Log
import com.megalexa.models.blocks.BlockTwitterWrite
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BlockTwitterWriteTest: BlockTest {

    @Test
    override fun validBlock() {
        val block = BlockTwitterWrite()
        assertEquals(block.getInformation(), "Twitter write block created")
    }

    @Test
    override fun invalidBLock() {
        val block = BlockTwitterWrite()
        assertNotEquals(block.getInformation(), "Twitter write block not created")
    }

    @Test
    fun getAccessKey() {
        val block = BlockTwitterWrite()
        assertNotNull(block.getAccessKey())
    }

    @Test
    fun getAccessSecret() {
        val block = BlockTwitterWrite()
        assertNotNull(block.getAccessSecret())
    }

    @Test
    fun getConsumerKey() {
        val block = BlockTwitterWrite()
        assertNotNull(block.getConsumerKey())
    }

    @Test
    fun getConsumerSecret() {
        val block = BlockTwitterWrite()
        assertNotNull(block.getConsumerSecret())
    }
}