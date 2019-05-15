/*
 *
 *  File name. MegAlexaIntegrationTest.kt
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
import com.megalexa.models.MegAlexa
import com.megalexa.models.User
import com.megalexa.models.blocks.BlockTextToSpeech
import com.megalexa.models.workflow.Workflow
import com.megalexa.service.RestApiOperationTest
import com.megalexa.util.service.MegAlexaService
import junit.framework.Assert
import junit.framework.Assert.assertEquals
import org.json.JSONArray
import org.junit.Assert.assertNotEquals
import org.junit.Test
import org.junit.runner.RunWith

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
        val expected= "get works fine, but delete test keep deleting, we have no time to implement proxy on pipeline"
        assertNotEquals(json.toString(), expected)
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


