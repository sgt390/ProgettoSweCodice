package com.megalexa.service.integration

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.megalexa.models.blocks.BlockTextToSpeech
import com.megalexa.models.blocks.Filter
import com.megalexa.models.workflow.Workflow
import com.megalexa.service.conversion.ConversionTest
import com.megalexa.util.service.WorkflowService.convertFromJSON
import com.megalexa.util.service.WorkflowService
import junit.framework.Assert.assertEquals
import org.json.JSONArray
import org.json.JSONObject
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class WorkflowConversionTest: ConversionTest {

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        Assert.assertEquals("com.megalexa", appContext.packageName)
    }

    @Test
    override fun conversionFromJSontoObject() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        /*
        val jsonObject= WorkflowService.getOperation(
            listOf(Pair("userID","dummyUID"),Pair("workflowName","workflow")))

        //doesn't workflow shit here

        var expected= Workflow("workflow")
        expected.addBlock(BlockTextToSpeech("This is the second block"))

        Assert.assertEquals(expected, workflow)
        */
    }
    override fun conversionFromObjectToJSon() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @Test
    fun valid() {

        val workflow= Workflow("pizza")

        workflow.addBlock(BlockTextToSpeech("This is the second block"))
        workflow.addBlock(Filter(2))

        val json= WorkflowService.convertToJSON(workflow)
        assertEquals(json.toString(),"prova")

    }


}