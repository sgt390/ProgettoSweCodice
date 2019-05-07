package com.megalexa.models.blocks

import android.provider.ContactsContract.CommonDataKinds.Email
import com.megalexa.models.connectors.Connector
import org.json.JSONObject

class BlockReadEmail(private val Token_Gmail : JSONObject):Block,Filtrable {
    override fun getInformation():String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun generateConnector(email: Email): Connector{
        TODO(reason = "not implemented")
    }
    fun valid(): Boolean {
        TODO(reason = "not implemented")
    }

}