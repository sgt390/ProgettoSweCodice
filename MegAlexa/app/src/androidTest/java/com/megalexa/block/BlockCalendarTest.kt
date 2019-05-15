/*
 *
 *  File name. BlockCalendarTest.kt
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
import com.megalexa.models.blocks.BlockCalendar
import org.junit.Assert.assertNotEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BlockCalendarTest:BlockTest {

    @Test
    override fun validBlock() {
        val block= BlockCalendar("askjdlaksjdla","zdnvdkn")
        assertNotEquals(block.getClientId(),"something")
    }

    @Test
    override fun invalidBLock() {
        val block= BlockCalendar("askjdlaksjdla","zdnvdkn")
        assertNotEquals(block.getClientId(),"something")
    }
}