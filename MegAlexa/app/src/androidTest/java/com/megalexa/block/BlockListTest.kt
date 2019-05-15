/*
 *
 *  File name. BlockFilterTest.kt
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
import com.megalexa.models.blocks.BlockList
import junit.framework.Assert
import org.json.JSONArray
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BlockListTest :BlockTest {

    @Test
    override fun invalidBLock() {
        var expected= JSONArray("[\"one\", \"two\"]")
        org.junit.Assert.assertNotEquals("[\"one\", \"three\"]", expected)
    }

    @Test
    override fun validBlock() {
        var validList = JSONArray("[\"one\", \"two\"]")
        val block= BlockList(validList)
        Assert.assertEquals(block.getList(), validList)
    }

}