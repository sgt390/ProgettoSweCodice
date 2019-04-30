package com.megalexa.models.blocks

import org.json.JSONObject

class BlockCalendar(): Block, Filtrable {
    override fun getInformation() :String {
        return "Calendar block"
    }

    fun testConnection(): Boolean{
        TODO("not implemented")
    }

    override fun toJSON(): JSONObject {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}