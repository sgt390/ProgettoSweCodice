/*
 *
 *  File name. UserConversionTest.kt
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
import com.megalexa.models.User
import com.megalexa.util.service.UserService
import junit.framework.Assert.assertEquals
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserConversionTest :ConversionTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        Assert.assertEquals("com.megalexa", appContext.packageName)
    }

    @Test
    override fun conversionFromJSontoObject() {
        val user= User("id3","prova@prova.com","prova")
        val convertedUser= UserService.convertFromJSON(UserService.convertToJSON(user))
        assertEquals(user.getID(),convertedUser.getID())
    }

    @Test
    override fun conversionFromObjectToJSon() {
        val user= User("id3","prova@prova.com","prova")
        val json= UserService.convertToJSON(user)
        assertEquals(json.toString(),"{\"userID\":\"id3\",\"name\":\"prova\",\"email\":\"prova@prova.com\"}")
    }
}