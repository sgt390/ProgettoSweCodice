/*
 *
 *  File name. GetWorkflowTest.kt
 *  Version: 1.0.0
 *  Date: 2019-04-15
 *  Author: Ludovico Brocca
 *  License:
 *  History:
 *  Author            || Date            || Description
 *  Ludovico Brocca   || 2019-04-15     || File created
 *  Gian Marco Bratzu || 2019-04-16     || Verifying code
 *
 */
package com.megalexa.service.integration


import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.megalexa.util.service.WorkflowService
import org.json.JSONArray
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class GetWorkFlowTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.megalexa", appContext.packageName)
    }

    @Test
    fun valid() {
        val jsonObject= WorkflowService.getOperation(
            listOf(Pair("userID","amzn1.account.AEUNVRHMHE2VKRFZWW72HELB4ZBQ"),Pair("workflowName","prova")))

        val expected= JSONArray("[\n" +
                "  {\n" +
                "    \"config\": {\n" +
                "      \"textToSpeech\": \"icsdi\"\n" +
                "    },\n" +
                "    \"blockType\": \"textToSpeech\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"config\": {\n" +
                "      \"textToSpeech\": \"ciao\"\n" +
                "    },\n" +
                "    \"blockType\": \"textToSpeech\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"config\": {\n" +
                "      \"textToSpeech\": \"3\"\n" +
                "    },\n" +
                "    \"blockType\": \"textToSpeech\"\n" +
                "  }\n" +
                "]")

        assertEquals(expected.toString(),jsonObject.get("content").toString())
    }

}
