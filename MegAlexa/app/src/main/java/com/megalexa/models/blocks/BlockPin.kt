/*
 * File: BlockPin.kt
 * Version: 1.0.0
 * Date: 2019-03-27
 * Author:  Gian Marco Bratzu
 *
 * License:
 *
 * History:
 * Author             || Date         || Description
 * Gian Marco Bratzu  || 2019-03-27   || creating file and header
 */
package com.megalexa.models.blocks
import org.json.JSONObject

class BlockPin(private var pin: Int):Block {

    override fun getInformation(): String {
        return "Contains pin to block workflow activity"
    }

    /* Return the pin
    *  @return pin
    */
    fun pin():Int{
        return pin
    }


    //Set method
    fun setPin(newPin: Int){
        pin = newPin
    }

    override fun toJSON() : JSONObject{
        val allBlock : JSONObject = JSONObject()
        allBlock.put("blockType", "Pin")
        val config : JSONObject = JSONObject()
        config.put("Pin", pin )
        allBlock.put("config", config)
        return allBlock
    }
}