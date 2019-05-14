/*
 *
 *  File name: BlockBorsaService.kt
 *  Version: 1.0.0
 *  Date: 2019-03-12
 *  Author: Ludovico Brocca
 *  License:
 *  History:
 *  Author          || Date            || Description
 *  Ludovico Brocca || 2019-03-12      || File created
 *  Mirko Franco    || 2019-03-19      || Verifying code
 *
 */

package com.megalexa.util.service

import com.megalexa.models.blocks.BlockBorsa
import org.json.JSONObject

object BlockBorsaService :BlockService() {

    override fun convertFromJSON(jsonObject: JSONObject): BlockBorsa {
        return BlockBorsa(jsonObject.getJSONObject("config").getString("URL"))
    }

    override fun <BlockBorsa> convertToJSON(t: BlockBorsa): JSONObject {
        val blockBorsa = t as com.megalexa.models.blocks.BlockBorsa
        val allBlock = JSONObject()
        allBlock.put("blockType", "Borsa")
        val config = JSONObject()
        config.put("URL" , blockBorsa.url())
        allBlock.put("config", config)
        return allBlock
    }
}