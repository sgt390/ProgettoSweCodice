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
* Matteo Depascale      || 2019-04-27   || Create file
* Andrea Deidda         || 2019-05-02   || Verifying code
*/

package com.megalexa.util.service

import com.megalexa.models.blocks.BlockList
import org.json.JSONObject

object BlockListService : BlockService() {

    override fun convertFromJSON(jsonObject: JSONObject): BlockList {
        val list = jsonObject.getJSONObject("config").getJSONArray("List")
        return BlockList(list)
    }

    override fun <BlockList> convertToJSON(t: BlockList): JSONObject {
        val listBlock = t as com.megalexa.models.blocks.BlockList
        val allBlock = JSONObject()
        allBlock.put("blockType", "List")
        val config = JSONObject()
        config.put("List", listBlock.getList())
        allBlock.put("config", config)

        return allBlock
    }
}