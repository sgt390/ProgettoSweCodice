/*
 *
 *  File name: BlockCryptoService.kt
 *  Version: 1.0.0
 *  Date: 2019-03-12
 *  Author:
 *  License:
 *  History:
 *  Author          || Date            || Description
 *  Ludovico Brocca || 2019-03-12      || File created
 *  Mirko Franco    || 2019-03-19      || Verifying code
 *
 */

package com.megalexa.util.service

import com.megalexa.models.blocks.BlockCrypto
import org.json.JSONObject

object BlockCryptoService :BlockService() {

    override fun convertFromJSON(jsonObject: JSONObject): BlockCrypto {
        return BlockCrypto(jsonObject.getJSONObject("config").getString("URL"))
    }

    override fun <BlockCrypto> convertToJSON(t: BlockCrypto): JSONObject {
        val blockCrypto = t as com.megalexa.models.blocks.BlockCrypto
        val allBlock = JSONObject()
        allBlock.put("blockType", "Crypto")
        val config = JSONObject()
        config.put("URL", blockCrypto.url())
        allBlock.put("config", config)
        return allBlock

    }
}