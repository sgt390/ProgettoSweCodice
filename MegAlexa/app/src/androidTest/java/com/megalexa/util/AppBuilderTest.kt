package com.megalexa.util


import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.megalexa.models.MegAlexa
import com.megalexa.models.User
import com.megalexa.models.blocks.BlockTextToSpeech
import com.megalexa.models.workflow.Workflow
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class AppBuilderTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.megalexa", appContext.packageName)
    }

    @Test
    fun valid() {

        val workflows =ArrayList<Workflow>()
        val workflow= Workflow("first")
        workflow.addBlock(BlockTextToSpeech("prova"))
        workflow.addBlock(BlockTextToSpeech("prova2"))
        workflows.add(workflow)
        val user= User("1","zeroseven","zerosevenswe@gmail.com")

        val app = MegAlexa.workflows(workflows).user(user).build()
        val assert= app.getWorkflowList().get(0)

        assertEquals(true, assert.getCount()==2)
    }

}