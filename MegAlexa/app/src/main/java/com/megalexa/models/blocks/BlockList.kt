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

package com.megalexa.models.blocks

import org.json.JSONArray
import org.json.JSONObject

class BlockList(private var list: JSONArray) : Block {
    override fun getInformation(): String {
        return "List"
    }

    override fun toJSON(): JSONObject {
        return JSONObject()
    }

    fun listUtilities(): List<String>{
        return listOf()
    }

    fun getList(): JSONArray{
        return list
    }
}