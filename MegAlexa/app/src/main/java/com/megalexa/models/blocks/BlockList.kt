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

import org.json.JSONObject

class BlockList() : Block {
    override fun getInformation(): String {
        return ""
    }

    override fun toJSON(): JSONObject {
        return JSONObject()
    }

    fun listUtilities(): List<String>{
        return listOf()
    }
}