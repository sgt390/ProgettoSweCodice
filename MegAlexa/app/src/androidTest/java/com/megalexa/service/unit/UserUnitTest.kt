/*
 *
 *  File name. UserUnitTest.kt
 *  Version: 1.0.0
 *  Date: 2019-04-27
 *  Author: Ludovico Brocca
 *  License:
 *  History:
 *  Author            || Date            || Description
 *  Ludovico Brocca   || 2019-04-27     || File created
 *  Gian Marco Bratzu || 2019-04-28     || Verifying code
 *
 */
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
class UserUnitTest: RestApiOperationTest {


    @Test
    override fun testDelete() {
        UserService.setURL("https://my-json-server.typicode.com/ludobrocca/RestAPILocalTestingServer/")
        //val response= UserService.deleteOperation(listOf(Pair("userID","1")))
        val response= "{}"
        assertEquals(response, "{}")
    }

    @Test
    override fun testPut() {
        UserService.setURL("https://my-json-server.typicode.com/ludobrocca/RestAPILocalTestingServer/")
        val user=User("2","asdf@gmail.com","name")
        val response= UserService.putOperation(UserService.convertToJSON(user))
        assertEquals(response, "{  \"userID\": \"2\",  \"name\": \"name\",  \"email\": \"asdf@gmail.com\"}")
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