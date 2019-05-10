package com.megalexa.service.unit

import android.support.test.runner.AndroidJUnit4
import com.megalexa.models.MegAlexa
import com.megalexa.models.User
import com.megalexa.service.RestApiOperationTest
import com.megalexa.util.service.MegAlexaService
import com.megalexa.util.service.UserService
import junit.framework.Assert
import junit.framework.Assert.assertEquals
import org.json.JSONObject
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AllUnitTest :RestApiOperationTest{

    @Test
    override fun testDelete() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @Test
    override fun testGet() {
        MegAlexaService.setURL("https://my-json-server.typicode.com/ludobrocca/RestAPILocalTestingServer/")
        val json= MegAlexaService.getOperation(listOf(Pair("userID","1")))
        assertEquals(json.get("response"), "OK")
    }

    @Test
    override fun testPost() {
        MegAlexaService.setURL("https://my-json-server.typicode.com/ludobrocca/RestAPILocalTestingServer/")
        val user= User("2","asdf@gmail.com","name")
        val app= MegAlexa.user(user).build()
        val response=MegAlexaService.postOperation(JSONObject())
        val json= JSONObject(response)
        assertEquals(response,"{}")
    }

    @Test
    override fun testPut() {
        TODO()
    }
}