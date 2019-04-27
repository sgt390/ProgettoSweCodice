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

import com.megalexa.models.blocks.BlockList
import com.megalexa.models.blocks.BlockPin
import org.json.JSONObject

object BlockListService : BlockService() {

    //TODO
    override fun convertFromJSON(jsonObject: JSONObject): BlockList {
        val list = jsonObject.getJSONObject("config").getString("List")
        return BlockList()
    }

    //TODO
    override fun <BlockList> convertToJSON(t: BlockList): JSONObject {
        val listBlock = t as com.megalexa.models.blocks.BlockList
        val allBlock = JSONObject()
        allBlock.put("blockType", "List")
        val config = JSONObject()
        config.put("List", listBlock.listUtilities())
        allBlock.put("config", config)
        return allBlock
    }
}