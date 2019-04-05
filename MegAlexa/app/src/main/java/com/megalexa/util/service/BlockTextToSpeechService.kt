package com.megalexa.util.service

import com.megalexa.models.blocks.BlockTextToSpeech
import org.json.JSONObject

object BlockTextToSpeechService : BlockService() {

    override fun convertFromJSON(jsonObject: JSONObject): BlockTextToSpeech {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <BlockTextToSpeech> convertToJSON(t: BlockTextToSpeech): JSONObject {
        val textBlock = t as com.megalexa.models.blocks.BlockTextToSpeech
        val allBlock = JSONObject()
        allBlock.put("blockType", "TextToSpeech")
        val config = JSONObject()
        config.put("TextToSpeech", textBlock.textBox() )
        allBlock.put("config", config)
        return allBlock
    }
}