package com.megalexa.util.service

import org.json.JSONObject

interface JSONConverter {

    fun <T> convertToJSON(t:T): JSONObject

    open fun convertFromJSON(jsonObject: JSONObject):Any

}