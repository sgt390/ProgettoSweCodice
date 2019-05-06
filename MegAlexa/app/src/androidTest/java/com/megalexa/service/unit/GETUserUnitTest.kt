package com.megalexa.service.unit

import android.support.test.runner.AndroidJUnit4
import com.megalexa.models.User
import com.megalexa.service.RestApiOperationTest
import com.megalexa.util.service.UserService
import junit.framework.Assert.assertEquals
import org.json.JSONObject
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GETUserUnitTest: RestApiOperationTest {


    @Test
    override fun testDelete() {
        TODO()
    }

    @Test
    override fun testPut() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @Test
    override fun testGet() {
        UserService.setURL("https://my-json-server.typicode.com/ludobrocca/RestAPILocalTestingServer/")
        val json= UserService.getOperation(listOf(Pair("userID","1")))
        assertEquals(json.get("name"),"Example")
    }

    @Test
    override fun testPost() {
        UserService.setURL("https://my-json-server.typicode.com/ludobrocca/RestAPILocalTestingServer/")
        val user=User("2","asdf@gmail.com","name")
        val response=UserService.postOperation(UserService.convertToJSON(user))
        val json= JSONObject(response)
        assertEquals(json.get("email"),"asdf@gmail.com")
    }
}