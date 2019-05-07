package com.megalexa.service.unit

import android.support.test.runner.AndroidJUnit4
import com.megalexa.models.blocks.BlockTextToSpeech
import com.megalexa.models.workflow.Workflow
import com.megalexa.service.RestApiOperationTest
import com.megalexa.util.service.WorkflowService
import junit.framework.Assert.assertEquals
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

        assertEquals(jsonObject.toString(),"[\n" +
                "      \t\t{\n" +
                "\t\t\"id\" : \"userID=1?workflowName=prova\",\n" +
                "        \t\"blockType\": \"TextToSpeech\",\n" +
                "        \t\"config\": {\n" +
                "         \t\t \"TextToSpeech\": \"first workflow created\"\n" +
                "       \t\t}\n" +
                "      }\n" +
                "    ]")

    }

    @Test
    override fun testPost() {
        WorkflowService.setURL("https://my-json-server.typicode.com/ludobrocca/RestAPILocalTestingServer/")
        val workflow= Workflow("post")
        workflow.addBlock(BlockTextToSpeech("first workflow created"))
        val string= WorkflowService.postOperation(WorkflowService.convertToJSON(workflow))

        assertEquals(string," ")

    }

    @Test
    override fun testPut() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}