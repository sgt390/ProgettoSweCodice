package com.megalexa.models.blocks

import com.megalexa.models.workflow.Workflow
import org.json.JSONObject

interface Block {

    fun getInformation():String

    fun toJSON() : JSONObject

}