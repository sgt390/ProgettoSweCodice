package com.megalexa.util.service

import org.json.JSONObject

object UserService: Service() {

    override fun <User> convertFromJSON(jsonObject: JSONObject): User{
        TODO()
    }


    override fun <User> convertToJSON(t: User): JSONObject {
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