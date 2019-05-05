package com.megalexa.service.integration


import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.util.Log
import com.megalexa.models.connectors.ConnectorSport
import com.megalexa.models.workflow.Workflow
import com.megalexa.util.service.UserService
import com.megalexa.util.service.WorkflowService
import org.json.JSONArray
import org.json.JSONObject

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

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
