/*
 *
 *  File name. BlockTwitterConversionTest.kt
 *  Version: 1.0.0
 *  Date: 2019-04-20
 *  Author: Ludovico Brocca
 *  License:
 *  History:
 *  Author            || Date            || Description
 *  Ludovico Brocca   || 2019-04-20      || File created
 *  Gian Marco Bratzu || 2019-04-21     || Verifying code
 *
 */

package com.megalexa.service.conversion

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.megalexa.models.blocks.BlockTwitter
import com.megalexa.util.service.BlockTwitterService
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BlockTwitterConversionTest: ConversionTest {

    val sampleUser="@BillGates"

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.megalexa", appContext.packageName)
    }

    @Test
    override fun conversionFromJSontoObject() {
        val expected = BlockTwitter(sampleUser)
        val json = BlockTwitterService.convertToJSON(expected)
        val block = BlockTwitterService.convertFromJSON(json)

        assertTrue(
            block.getAccessKey().equals("1110935101561556992-b9BpCfXw3NqSbzhEpMtvmvbVMqGE2N") &&
                    block.getAccessSecret().equals("OMVvSqVFjCOC0uFQkelIycpvCgUvOWFht8COIkXSUWXUH") &&
                    block.getConsumerKey().equals("Bdc0zcGkYm6ykEoiw4NJUZxMO") &&
                    block.getConsumerSecret().equals("FSgU3qIVe6gvLg4NLkKnZYIHFWQHNLMrKYCGoHR5pjUz0IPaRP") &&
                    block.getScreenName().equals(sampleUser)
        )
    }

    @Test
    override fun conversionFromObjectToJSon() {
        val expected = BlockTwitter(sampleUser)
        val json = BlockTwitterService.convertToJSON(expected)
        val config = "{\"blockType\":\"TwitterUserTL\",\"config\":{\"access_token_key\":\"" +
                "1110935101561556992-b9BpCfXw3NqSbzhEpMtvmvbVMqGE2N\",\"access_token_secret\":\"" +
                "OMVvSqVFjCOC0uFQkelIycpvCgUvOWFht8COIkXSUWXUH\",\"consumer_key\":\"Bdc0zcGkYm6ykEoiw4NJUZxMO\",\"" +
                "consumer_secret\":\"FSgU3qIVe6gvLg4NLkKnZYIHFWQHNLMrKYCGoHR5pjUz0IPaRP\",\"screenName\":\"" +
                "@BillGates\"}}"

        assertEquals(json.toString(),config)
    }
}