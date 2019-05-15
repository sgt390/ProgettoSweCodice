/*
 *
 *  File name. WorkflowComversionTest.kt
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
import com.megalexa.models.blocks.BlockTextToSpeech
import com.megalexa.models.blocks.Filter
import com.megalexa.models.workflow.Workflow
import com.megalexa.service.conversion.ConversionTest
import com.megalexa.util.service.WorkflowService.convertFromJSON
import com.megalexa.util.service.WorkflowService
import com.megalexa.util.service.WorkflowService.convertToJSON
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
        val jsonObject= WorkflowService.getOperation(
            listOf(Pair("userID","dummyUID"),Pair("workflowName","workflowNotCancelled")))

        var workflow = convertFromJSON(jsonObject)

        var expected= Workflow("workflowNotCancelled")
        expected.addBlock(BlockTextToSpeech("This is the first block"))

        Assert.assertEquals(expected, workflow)

    }

    @Test
    override fun conversionFromObjectToJSon() {
        var workflow= Workflow("workflowNotCancelled")
        workflow.addBlock(BlockTextToSpeech("This is the first block"))

        val jsonOBJ = convertToJSON(workflow)

        val expected =  "{\"userID\":\"dummyUID\",\"workflowName\":\"workflownotcancelled\",\"workflow\":[{\"blockType\":\"TextToSpeech\",\"config\":{\"TextToSpeech\":\"This is the first block\"}}]}"
        Assert.assertEquals(expected, jsonOBJ.toString())
    }

    @Test
    fun valid() {

        val workflow= Workflow("pizza")

        workflow.addBlock(BlockTextToSpeech("This is the second block"))
        workflow.addBlock(Filter(2))

        val json= WorkflowService.convertToJSON(workflow)
        val expected = "{\"userID\":\"dummyUID\",\"workflowName\":\"pizza\",\"workflow\":[{\"blockType\":\"TextToSpeech\",\"config\":{\"TextToSpeech\":\"This is the second block\"}},{\"blockType\":\"Filter\",\"config\":{\"limit\":2}}]}"
        assertEquals(json.toString(), expected)

    }


}