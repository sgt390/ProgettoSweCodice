/*
 *
 *  File name. BlockTextToSpeechTest.kt
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
import com.megalexa.models.blocks.BlockTextToSpeech
import com.megalexa.util.InvalidBlockException
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BlockTextToSpeechTest:BlockTest {

    @Test
    override fun invalidBLock() {
        var expected= "no errors found"
        try {
            val block= BlockTextToSpeech("this text is longer than 256 characters this text is longer than 256 characters this text is longer than 256 characters this text is longer than 256 characters this text is longer than 256 characters this text is longer than 256 characters this text is longer than 256 characters this text is longer than 256 characters this text is longer than 256 characters this text is longer than 256 characters this text is longer than 256 characters this text is longer than 256 characters this text is longer than 256 characters this text is longer than 256 characters this text is longer than 256 characters this text is longer than 256 characters this text is longer than 256 characters")
        }catch (e: InvalidBlockException) {
            expected= e.getErrorMessage()
        }

        assertEquals(expected, "Text too long, please stay within 256 character")
    }

    @Test
    override fun validBlock() {
        val validText= "this text is valid"
        val block= BlockTextToSpeech(validText)
        assertEquals(block.textBox(), validText)}
}