package com.megalexa.service.integration

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.megalexa.models.MegAlexa
import com.megalexa.models.User
import com.megalexa.models.blocks.BlockTextToSpeech
import com.megalexa.models.workflow.Workflow
import com.megalexa.service.RestApiOperationTest
import com.megalexa.util.service.MegAlexaService
import junit.framework.Assert
import org.json.JSONArray
import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class MegAlexaIntegrationTest: RestApiOperationTest {

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.megalexa", appContext.packageName)
    }

    @Test
    override fun testDelete() {
        val json= MegAlexaService.getOperation(listOf(Pair("userID","dummyUID")))
        val expected= ""
        assertNotEquals(json.toString(), expected)
    }

    @Test
    override fun testGet() {
        val json= MegAlexaService.getOperation(listOf(Pair("userID","dummyUID")))
        val expected= "{\"userID\":\"dummyUID\",\"name\":\"dummyName\",\"email\":\"dummyEmail\",\"workflowList\":{\"test\":[{\"config\":{\"TextToSpeech\":\"This is the second block\"},\"blockType\":\"TextToSpeech\"}],\"workflow\":[{\"config\":{\"TextToSpeech\":\"This is the second block\"},\"blockType\":\"TextToSpeech\"}],\"workflowtest\":[{\"config\":{\"TextToSpeech\":\"This is the first block\"},\"blockType\":\"TextToSpeech\"},{\"config\":{\"TextToSpeech\":\"This is the put block\"},\"blockType\":\"TextToSpeech\"}]}}"
        assertEquals(json.toString(), expected)
    }

    @Test
    override fun testPost() {
        val json= MegAlexaService.getOperation(listOf(Pair("userID","dummyUID")))
        val expected= ""
        assertNotEquals(json.toString(), expected)
    }


    @Test
    override fun testPut() {
        val json= MegAlexaService.getOperation(listOf(Pair("userID","dummyUID")))
        val expected= ""
        assertNotEquals(json.toString(), expected)
    }
}


