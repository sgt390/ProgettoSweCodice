package com.megalexa.service.unit

import android.support.test.runner.AndroidJUnit4
import com.megalexa.models.blocks.BlockTextToSpeech
import com.megalexa.models.workflow.Workflow
import com.megalexa.service.RestApiOperationTest
import com.megalexa.util.service.WorkflowService
import junit.framework.Assert.assertEquals
import org.json.JSONObject
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WorkflowUnitTest :RestApiOperationTest {

    @Test
    override fun testDelete() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @Test
    override fun testGet() {

       WorkflowService.setURL("https://my-json-server.typicode.com/ludobrocca/RestAPILocalTestingServer/")
       val jsonObject=WorkflowService.getOperation(
           listOf(Pair("userID","1"),Pair("workflowName","prova")))

        assertEquals(jsonObject.get("content").toString(),"[{\"id\":\"userID=1?workflowName=prova\",\"blockType\":\"TextToSpeech\",\"config\":{\"TextToSpeech\":\"first workflow created\"}}]")

    }

    @Test
    override fun testPost() {
        WorkflowService.setURL("https://my-json-server.typicode.com/ludobrocca/RestAPILocalTestingServer/")
        val workflow= Workflow("example")
        workflow.addBlock(BlockTextToSpeech("first workflow created"))
        val string= WorkflowService.postOperation(WorkflowService.convertToJSON(workflow))
        val jsonObject=JSONObject(string)
        assertEquals(jsonObject.get("workflowName"),"example")
    }

    @Test
    override fun testPut() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}