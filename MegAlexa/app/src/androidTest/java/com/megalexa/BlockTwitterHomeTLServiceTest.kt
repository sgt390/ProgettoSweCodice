package com.megalexa

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.megalexa.util.service.BlockTwitterService

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
class BlockTwitterHomeTLServiceTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.megalexa", appContext.packageName)
    }

    @Test
    fun validToJSON() {
        val expected = BlockTwitter("@BillGates")
        val json = BlockTwitterHomeTLService.convertToJSON(expected)
        val config = "{\"blockType\":\"TwitterUserTL\",\"config\":{\"access_token_key\":\"" +
                "1110935101561556992-b9BpCfXw3NqSbzhEpMtvmvbVMqGE2N\",\"access_token_secret\":\"" +
                "OMVvSqVFjCOC0uFQkelIycpvCgUvOWFht8COIkXSUWXUH\",\"consumer_key\":\"Bdc0zcGkYm6ykEoiw4NJUZxMO\",\"" +
                "consumer_secret\":\"FSgU3qIVe6gvLg4NLkKnZYIHFWQHNLMrKYCGoHR5pjUz0IPaRP\",\"screenName\":\"" +
                "@BillGates\"}}"

        assertEquals(json.toString(),config)
    }

    @Test
    fun validFromJSON() {
        val expected = BlockTwitter("@BillGates")
        val json = BlockTwitterHomeTLService.convertToJSON(expected)
        val block = BlockTwitterHomeTLService.convertFromJSON(json)

        assertTrue(
            block.getAccessKey().equals("1110935101561556992-b9BpCfXw3NqSbzhEpMtvmvbVMqGE2N") &&
                    block.getAccessSecret().equals("OMVvSqVFjCOC0uFQkelIycpvCgUvOWFht8COIkXSUWXUH") &&
                    block.getConsumerKey().equals("Bdc0zcGkYm6ykEoiw4NJUZxMO") &&
                    block.getConsumerSecret().equals("FSgU3qIVe6gvLg4NLkKnZYIHFWQHNLMrKYCGoHR5pjUz0IPaRP") &&
                    block.getScreenName().equals("@BillGates")
        )
    }
}