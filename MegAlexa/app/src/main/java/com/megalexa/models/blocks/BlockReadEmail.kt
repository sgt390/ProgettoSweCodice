package com.megalexa.models.blocks

import android.provider.ContactsContract.CommonDataKinds.Email
import com.megalexa.models.connectors.Connector
import org.json.JSONObject

class BlockReadEmail(private val email: Email):Block,Filtrable {
    override fun getInformation():String {
        return "Read mail block"
    }

    init {
        val connector: Connector = generateConnector(email)
    }
    private fun generateConnector(email: Email): Connector{
        TODO(reason = "not implemented")
    }
    fun valid(): Boolean {
        TODO(reason = "not implemented")
    }

    override fun toJSON(): JSONObject {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}