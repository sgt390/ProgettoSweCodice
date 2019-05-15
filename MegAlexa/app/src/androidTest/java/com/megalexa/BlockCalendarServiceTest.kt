/*
 *
 *  File name. BlockCalendarServicerTest.kt
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
package com.megalexa

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BlockCalendarServiceTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        Assert.assertEquals("com.megalexa", appContext.packageName)
    }

    @Test
    fun valid() {
//        val HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport()
//        val Cal : JSONObject = BlockCalendarService.getToken(BlockCalendarService.getCredentials(HTTP_TRANSPORT))
//        //Log.d("Token",Cal.accessToken)
//        junit.framework.Assert.assertEquals("prova", "prova")


    }

}