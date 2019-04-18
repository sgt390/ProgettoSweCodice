package com.megalexa.util.service

import org.json.JSONObject

interface JSONConverter {

    fun <T> convertToJSON(t:T, userID : String = "undefined"): JSONObject

    open fun convertFromJSON(jsonObject: JSONObject):Any

}