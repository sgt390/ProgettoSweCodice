/*
* File: BlockListService.kt
* Version: 1.0.0
* Date: 2019-04-27
* Author: Matteo Depascale
*
* License:
*
* History:
* Author                || Date         || Description
* Matteo Depascale      || 2019-02-17   || Create file
*                       ||              ||
*/

package com.megalexa.util.service

import com.megalexa.models.blocks.BlockPin
import org.json.JSONObject

object BlockListService : BlockService() {

    //TODO
    override fun convertFromJSON(jsonObject: JSONObject): BlockPin {
        val pin = jsonObject.getJSONObject("config").getString("PIN").toInt()
        return BlockPin(pin)
    }

    //TODO
    override fun <BlockPin> convertToJSON(t: BlockPin): JSONObject {
        val pinBlock = t as com.megalexa.models.blocks.BlockPin
        val allBlock = JSONObject()
        allBlock.put("blockType", "PIN")
        val config = JSONObject()
        config.put("PIN", pinBlock.pin().toString() )
        allBlock.put("config", config)
        return allBlock
    }
}