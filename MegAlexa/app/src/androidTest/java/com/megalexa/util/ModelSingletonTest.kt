/*
 *
 *  File name. ModelSingletonTest.kt
 *  Version: 1.0.0
 *  Date: 2019-05-02
 *  Author: Ludovico Brocca
 *  License:
 *  History:
 *  Author            || Date            || Description
 *  Ludovico Brocca   || 2019-05-02     || File created
 *  Gian Marco Bratzu || 2019-05-03     || Verifying code
 *
 */
package com.megalexa.util


import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.megalexa.models.MegAlexa
import com.megalexa.models.User
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

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