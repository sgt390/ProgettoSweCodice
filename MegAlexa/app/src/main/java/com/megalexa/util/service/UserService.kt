package com.megalexa.util.service

import com.megalexa.models.User
import org.json.JSONObject

object UserService: Service() {

    fun convertFromJSON(jsonObject: JSONObject): User{
        TODO()
    }

    fun convertToJSON(user:User): JSONObject{
        val userJ : JSONObject = JSONObject()
        userJ.put("userID", user.getID())
        userJ.put("name", user.getName())
        userJ.put("email", user.getMail())
        return userJ
    }

    override val resource: String
        get() = "user"
}