package com.megalexa.service.integration


import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.util.Log
import com.megalexa.models.blocks.BlockTextToSpeech
import com.megalexa.models.blocks.Filter
import com.megalexa.models.workflow.Workflow
import com.megalexa.util.service.WorkflowService
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PostWorkflowTest {

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        Assert.assertEquals("com.megalexa", appContext.packageName)
    }

    @Test
    fun valid() {

        val workflow= Workflow("mandolino")

        workflow.addBlock(BlockTextToSpeech("This is the second block"))
        workflow.addBlock(Filter(2))

        val json= WorkflowService.convertToJSON(workflow)
        Log.d("fhfhhfhfhhfhf",json.toString())
        json.put("userID","amzn1.account.AEUNVRHMHE2VKRFZWW72HELB4ZBQ")
        WorkflowService.postOperation(json)
        Log.d("json da copiare", json.toString())
        println(json.toString())

    }


}