/*
 *
 *  File name: BlockTextToSpeechService.kt
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

import com.megalexa.models.blocks.BlockTextToSpeech
import org.json.JSONObject

object BlockTextToSpeechService : BlockService() {

    override fun convertFromJSON(jsonObject: JSONObject): BlockTextToSpeech {
        return BlockTextToSpeech(jsonObject.getJSONObject("config").getString("TextToSpeech"))
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