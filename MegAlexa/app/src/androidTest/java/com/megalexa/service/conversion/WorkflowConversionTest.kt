/*
 *
 *  File name. WorkflowConversionTest.kt
 *  Version: 1.0.0
 *  Date: 2019-04-26
 *  Author: Ludovico Brocca
 *  License:
 *  History:
 *  Author            || Date            || Description
 *  Ludovico Brocca   || 2019-04-26      || File created
 *  Gian Marco Bratzu || 2019-04-27     || Verifying code
 *
 */
package com.megalexa.service.conversion

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.megalexa.models.workflow.Workflow
import com.megalexa.util.service.WorkflowService
import junit.framework.Assert.assertEquals
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WorkflowConversionTest :ConversionTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        Assert.assertEquals("com.megalexa", appContext.packageName)
    }

    @Test
    override fun conversionFromJSontoObject() {
        val workflow=Workflow("conversione")
        val convertedWorkflow=WorkflowService.convertFromJSON(WorkflowService.convertToJSON(workflow))
        assertEquals(workflow.getName(),convertedWorkflow.getName())
    }

    @Test
    override fun conversionFromObjectToJSon() {
        val workflow=Workflow("conversione")
        val json=WorkflowService.convertToJSON(workflow)
        assertEquals(json.toString(),"{\"userID\":\"dummyUID\",\"workflowName\":\"conversione\",\"workflow\":[]}")

    }
}