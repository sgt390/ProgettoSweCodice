package com.megalexa.service.integration

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.megalexa.util.service.UserService
import org.json.JSONObject
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class GetUserTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.megalexa", appContext.packageName)
    }

    @Test
    fun valid() {
        val param= "amzn1.account.AEFN6PFSKLCCFNVOH6GXZFGND5HA"

        val response =UserService.getOperation(listOf(Pair("userID",param)))
        val json= JSONObject("{\n" +
                "  \"userID\": \"amzn1.account.AEFN6PFSKLCCFNVOH6GXZFGND5HA\",\n" +
                "  \"name\": \"mirko.franco@icloud.com\",\n" +
                "  \"email\": \"Mirko\"\n" +
                "}")

        assertEquals(response.toString(),json.toString())
    }

}
