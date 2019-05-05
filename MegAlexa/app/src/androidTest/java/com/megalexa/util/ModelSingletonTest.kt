package com.megalexa.util

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.megalexa.models.MegAlexa
import com.megalexa.models.User
import com.megalexa.models.blocks.BlockTextToSpeech
import com.megalexa.models.workflow.Workflow


import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ModelSingletonTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.megalexa", appContext.packageName)
    }

    @Test
    fun singleton() {

        val instanceOne = MegAlexa.getInstance()
        val user=User("test","test@gmail.com","zeroseven")
        instanceOne.setUser(user)
        val secondInstance= MegAlexa.getInstance()
        assertEquals(instanceOne.getUser().getID(), secondInstance.getUser().getID())
    }

}