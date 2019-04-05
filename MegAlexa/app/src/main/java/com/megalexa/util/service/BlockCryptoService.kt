package com.megalexa.util.service

import com.megalexa.models.blocks.Block
import com.megalexa.models.blocks.BlockCrypto
import org.json.JSONObject

object BlockCryptoService :BlockService() {

    override fun convertFromJSON(jsonObject: JSONObject): BlockCrypto {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <BlockCrypto> convertToJSON(t: BlockCrypto): JSONObject {
        val blockCrypto = t as com.megalexa.models.blocks.BlockCrypto
        val allBlock = JSONObject()
        allBlock.put("blockType", "Crypto")
        val config = JSONObject()
        config.put("url", blockCrypto.url())
        allBlock.put("config", config)
        return allBlock

    }
}