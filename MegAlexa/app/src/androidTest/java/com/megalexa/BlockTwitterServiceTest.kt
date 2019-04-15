package com.megalexa

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.megalexa.util.service.BlockTwitterService

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
class BlockTwitterServiceTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.megalexa", appContext.packageName)
    }

    @Test
    fun validToJSON() {
        TODO()
    }

    @Test
    fun validFromJSON() {
        TODO()
    }

    /*
    @Test
    fun validToJSON() {
        val expected = BlockTwitter("1110935101561556992-b9BpCfXw3NqSbzhEpMtvmvbVMqGE2N",
                            "OMVvSqVFjCOC0uFQkelIycpvCgUvOWFht8COIkXSUWXUH","Bdc0zcGkYm6ykEoiw4NJUZxMO",
                            "FSgU3qIVe6gvLg4NLkKnZYIHFWQHNLMrKYCGoHR5pjUz0IPaRP","@BillGates")

        val json = BlockTwitterService.convertToJSON(expected)
        val config = "{\"blockType\":\"Twitter\",\"config\":{\"access_token_key\":
                \"1110935101561556992-b9BpCfXw3NqSbzhEpMtvmvbVMqGE2N\",\"access_token_secret\":
                \"OMVvSqVFjCOC0uFQkelIycpvCgUvOWFht8COIkXSUWXUH\",\"consumer_key\":\"Bdc0zcGkYm6ykEoiw4NJUZxMO\",
                \"consumer_secret\":\"FSgU3qIVe6gvLg4NLkKnZYIHFWQHNLMrKYCGoHR5pjUz0IPaRP\",\"screenName\":
                \"@BillGates\"}}"

        assertEquals(json.toString(),config)
    }

    @Test
    fun validFromJSON() {
        val expected = BlockTwitter("1110935101561556992-b9BpCfXw3NqSbzhEpMtvmvbVMqGE2N",
                            "OMVvSqVFjCOC0uFQkelIycpvCgUvOWFht8COIkXSUWXUH","Bdc0zcGkYm6ykEoiw4NJUZxMO",
                            "FSgU3qIVe6gvLg4NLkKnZYIHFWQHNLMrKYCGoHR5pjUz0IPaRP","@BillGates")

        val json = BlockTwitterService.convertToJSON(expected)
        val block = BlockTwitterService.convertFromJSON(json)

        assertTrue(block.get.equals("1110935101561556992-b9BpCfXw3NqSbzhEpMtvmvbVMqGE2N") &&
                    block.get.equals("OMVvSqVFjCOC0uFQkelIycpvCgUvOWFht8COIkXSUWXUH") &&
                    block.get.equals("Bdc0zcGkYm6ykEoiw4NJUZxMO") &&
                    block.get.equals("FSgU3qIVe6gvLg4NLkKnZYIHFWQHNLMrKYCGoHR5pjUz0IPaRP") &&
                    block.get.equals("@BillGates"))
    }
     */
}