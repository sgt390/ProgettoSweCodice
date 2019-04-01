package com.megalexa.util.service

import org.json.JSONObject

object MegAlexaService:Service() {

    override val resource: String
        get() = TODO()


    override fun <MegAlexaService> convertFromJSON(jsonObject: JSONObject): MegAlexaService {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <MegAlexaService> convertToJSON(t: MegAlexaService): JSONObject {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}