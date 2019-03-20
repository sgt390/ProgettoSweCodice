/*
 * File: BlockTextToSpeech.kt
 * Version: 1.0.0
 * Date: 2019-02-17
 * Author:  Andrea Deidda
 *          Matteo Depascale
 * License:
 *
 * History:
 * Author           || Date         || Description
 * Andrea Deidda    || 2019-02-07   || creating file and header
 * Andrea Deidda    || 2019-02-20   || implementing functions
 * Matteo Depascale || 2019-02-24   || verified BlockTextToSpeech
 */
package com.megalexa.models.blocks

import android.webkit.JsPromptResult
import org.json.JSONObject

class BlockTextToSpeech(private var textBox: String):Block {
    init{
        //Control variable textBox's character number
        require(textBox.length <= 256){
            println("Text too long, please stay within 256 character")
        }
    }
    /* getInformation()
    *  @return String
    */
    override fun getInformation(): String {
        return "Contains personalized text"
    }

    /* Return the text the user inserted
    *  @return String text
    */
    fun textBox()=textBox


    //Set method
    fun setText(text: String){
        textBox = text
    }

    override fun toJSON() : JSONObject{
        val allBlock : JSONObject = JSONObject()
        allBlock.put("blockType", "TextToSpeech")
        val config : JSONObject = JSONObject()
        config.put("TextToSpeech", textBox )
        allBlock.put("config", config)
        return allBlock
    }
}