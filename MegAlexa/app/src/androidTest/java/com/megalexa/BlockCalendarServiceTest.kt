package com.megalexa

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.util.Log
import com.google.api.client.auth.oauth2.Credential
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.megalexa.util.service.BlockCalendarService
import org.json.JSONObject
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