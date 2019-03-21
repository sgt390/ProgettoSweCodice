package com.megalexa.util.service

import com.megalexa.models.blocks.Block
import com.megalexa.models.blocks.BlockTextToSpeech
import org.json.JSONObject

class BlockTextToSpeechService : BlockService() {

    override fun convertFromJSON(jsonObject: JSONObject): Block {
        TODO()
    }

    override fun convertToJSON(block: Block): JSONObject {

        val textBlock = block as BlockTextToSpeech
        val allBlock : JSONObject = JSONObject()
        allBlock.put("blockType", "TextToSpeech")
        val config : JSONObject = JSONObject()
        config.put("TextToSpeech", textBlock.textBox() )
        allBlock.put("config", config)
        return allBlock
    }
}