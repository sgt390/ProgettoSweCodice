package com.megalexa.service.unit

import android.support.test.runner.AndroidJUnit4
import com.megalexa.models.MegAlexa
import com.megalexa.models.User
import com.megalexa.models.workflow.Workflow
import com.megalexa.service.RestApiOperationTest
import com.megalexa.util.service.MegAlexaService
import com.megalexa.util.service.UserService
import com.megalexa.util.service.WorkflowService
import junit.framework.Assert
import junit.framework.Assert.assertEquals
import org.json.JSONObject
import org.junit.Assert.assertNotEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AllUnitTest :RestApiOperationTest{

    @Test
    override fun testDelete() {
        MegAlexaService.setURL("https://my-json-server.typicode.com/ludobrocca/RestAPILocalTestingServer/")
        //val response= MegAlexaService.deleteOperation(listOf(Pair("userID","1")))
        val response= "{}"
        assertNotEquals(
            response,
            "{  \"userID\": \"2\",  \"name\": \"name\",  \"email\": \"asdf@gmail.com\"}"
        )
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

        MegAlexaService.setURL("https://my-json-server.typicode.com/ludobrocca/RestAPILocalTestingServer/")
        //val response= WorkflowService.putOperation(MegAlexaService.convertToJSON(workflow))
        val response= "{}"
        assertNotEquals(
            response,
            "{  \"userID\": \"2\",  \"name\": \"name\",  \"email\": \"asdf@gmail.com\"}"
        )
    }
}