package com.megalexa


import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ConnectorReadTwitterTest: ConnectorTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.megalexa", appContext.packageName)
    }

    @Test
    override fun validConnector() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @Test
    override fun nonValidConnector() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    //@Test
    /*fun valid() {
        val someaccount = "someAccount@gmail.com"
        val password=""
        val accountIsValid = ConnectorReadTwitter(someaccount,password).valid()
        assertEquals(true, accountIsValid)
    }*/

}
