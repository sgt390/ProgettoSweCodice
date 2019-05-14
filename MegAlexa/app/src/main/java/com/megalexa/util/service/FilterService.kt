/*
 *
 *  File name:
 *  Version:
 *  Date:
 *  Author:
 *  License:
 *  History:
 *  Author        || Date            || Description
 * /
 *
 */

package com.megalexa.util.service

import com.megalexa.models.blocks.Filter
import org.json.JSONObject

object FilterService: BlockService() {
    override fun convertFromJSON(jsonObject: JSONObject): Filter {
        val limit = jsonObject.getJSONObject("config").getInt("limit").toShort()
        return Filter(limit)
    }

    override fun <Filter> convertToJSON(t: Filter): JSONObject {
        val filter = t as com.megalexa.models.blocks.Filter
        val allBlock = JSONObject()
        allBlock.put("blockType", "Filter" )
        val config = JSONObject()
        config.put("limit" , filter.limit().toInt())
        allBlock.put("config", config)
        return allBlock
    }
}