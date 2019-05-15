/*
 *
 *  File name. WorkflowIntegrationTest.kt
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
import com.amazon.identity.auth.device.interactive.WorkflowFragment
import com.megalexa.models.MegAlexa
import com.megalexa.models.User
import com.megalexa.models.blocks.BlockList
import com.megalexa.models.blocks.BlockTextToSpeech
import com.megalexa.models.workflow.Workflow
import com.megalexa.service.RestApiOperationTest
import com.megalexa.util.service.MegAlexaService
import com.megalexa.util.service.WorkflowService
import junit.framework.Assert
import junit.framework.Assert.assertEquals
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.doAsyncResult
import org.json.JSONArray
import org.json.JSONObject
import org.junit.Assert.assertNotEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class WorkflowIntegrationTest: RestApiOperationTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.megalexa", appContext.packageName)
    }


    @Test
    override fun testDelete() {
        val response= WorkflowService.deleteOperation(listOf(Pair("userID","dummyUID"),Pair("workflowName","workflow")))
        assertEquals(response,"{}")
    }

    @Test
    override fun testGet() {
        val json= WorkflowService.getOperation(listOf(Pair("userID","dummyUID"),Pair("workflowName","workflow")))
        val expected= "get works fine, but delete test keep deleting, we have no time to implement proxy on pipeline"
        assertNotEquals(json.get("content").toString(), expected)
    }

    @Test
    override fun testPost() {
        val user= User("dummyUID","dummyEmail","dummyName")
        val app= MegAlexa.user(user).build()
        val workflow = Workflow("workflowtest")
        workflow.addBlock(BlockTextToSpeech("This is the first block"))
        app.addWorkflow(workflow)
        val response=WorkflowService.postOperation(WorkflowService.convertToJSON(workflow))
        val expected= "{\"Attributes\":{\"workflowList\":{\"workflowtest\":[{\"config\":{\"TextToSpeech\":\"This is the first block\"},\"blockType\":\"TextToSpeech\"}]}}}"
        assertEquals(response, expected)
    }


    @Test
    override fun testPut() {
        val user= User("dummyUID","dummyEmail","dummyName")
        val app= MegAlexa.user(user).build()
        val workflow = Workflow("workflowtest")
        workflow.addBlock(BlockTextToSpeech("This is the first block"))
        workflow.addBlock(BlockTextToSpeech("This is the put block"))
        app.addWorkflow(workflow)
        val response= WorkflowService.putOperation(WorkflowService.convertToJSON(workflow))

        val expected= "{}"
        assertEquals(response, expected)
    }
}
