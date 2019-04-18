package com.megalexa.util.service

import com.megalexa.models.User
import org.json.JSONObject


object UserService: Service() {

    override fun convertFromJSON(jsonObject: JSONObject): User {
        return  com.megalexa.models.User(jsonObject.get("userID").toString(),jsonObject.get("name").toString(),jsonObject.get("email").toString())
    }

    override fun <User> convertToJSON(t: User, userID : String): JSONObject {
        val userJ = JSONObject()
        val user= t as com.megalexa.models.User
        userJ.put("userID", user.getID())
        userJ.put("name", user.getName())
        userJ.put("email", user.getMail())
        return userJ
    }
    override val resource: String
        get() = "user"
}