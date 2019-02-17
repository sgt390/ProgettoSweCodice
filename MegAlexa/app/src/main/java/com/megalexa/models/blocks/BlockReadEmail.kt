package com.megalexa.models.blocks

import android.provider.ContactsContract.CommonDataKinds.Email
import com.megalexa.adapters.connectors.Connector

class BlockReadEmail(private val email: Email):Block,Filtrable {
    override fun getInformation() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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

    override var itemsToShow: Int
        get() = itemsToShow
        set(value) {
            itemsToShow=value
        }

    override var filtered: Boolean
        get() = filtered
        set(value) {
            filtered=value
        }


}