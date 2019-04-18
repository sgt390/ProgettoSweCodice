package com.megalexa.util.service

import com.megalexa.models.blocks.Block
import com.megalexa.models.blocks.BlockCrypto
import org.json.JSONObject

object BlockCryptoService :BlockService() {

    override fun convertFromJSON(jsonObject: JSONObject): BlockCrypto {
        return BlockCrypto(jsonObject.getJSONObject("config").getString("URL"))
    }

    override fun <BlockCrypto> convertToJSON(t: BlockCrypto, userID : String): JSONObject {
        val blockCrypto = t as com.megalexa.models.blocks.BlockCrypto
        val allBlock = JSONObject()
        allBlock.put("blockType", "Crypto")
        val config = JSONObject()
        config.put("URL", blockCrypto.url())
        allBlock.put("config", config)
        return allBlock

    }
}