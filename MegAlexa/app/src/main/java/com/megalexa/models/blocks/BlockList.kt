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
* Mirko Franco          || 2019-05-02   || Verifying code
*/

package com.megalexa.models.blocks

import com.megalexa.R
import com.megalexa.util.ApplicationContextProvider
import org.json.JSONArray

class BlockList(private var list: JSONArray) : Block {
    override fun getInformation(): String {
        return  ApplicationContextProvider.context!!.resources!!.getString(R.string.BlockList)
    }

    fun listUtilities(): List<String>{
        return listOf()
    }

    fun getList(): JSONArray {
        return list
    }
}