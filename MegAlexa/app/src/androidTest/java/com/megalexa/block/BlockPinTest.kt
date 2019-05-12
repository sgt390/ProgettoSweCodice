package com.megalexa.block

import android.support.test.runner.AndroidJUnit4
import com.megalexa.models.blocks.BlockPin
import junit.framework.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BlockPinTest: BlockTest{

    @Test
    override fun validBlock() {
        val validPIN = 1234
        val block = BlockPin(validPIN)
        assertEquals(block.pin(), validPIN)
    }

    @Test
    override fun invalidBLock() {
        val validPIN = 1234
        val block = BlockPin(validPIN)
        assertNotEquals(block.pin(), "1111")
    }
}