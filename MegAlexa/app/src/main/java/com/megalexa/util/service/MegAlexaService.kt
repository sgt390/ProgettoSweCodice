package com.megalexa.util.service

import com.megalexa.models.MegAlexa
import org.json.JSONObject

object MegAlexaService:Service() {

    override val resource: String
        get() = TODO()


    override fun convertFromJSON(jsonObject: JSONObject): MegAlexa {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <MegAlexa> convertToJSON(t: MegAlexa): JSONObject {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}