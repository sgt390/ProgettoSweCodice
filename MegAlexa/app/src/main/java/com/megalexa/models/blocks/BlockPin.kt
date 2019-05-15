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
 * Andrea Deidda      || 2019-04-02   || Verifying code
 */
package com.megalexa.models.blocks

import com.megalexa.R
import com.megalexa.util.ApplicationContextProvider

class BlockPin(private var pin: Int):Block {

    override fun getInformation(): String {
        return  ApplicationContextProvider.context!!.resources!!.getString(R.string.BlockPin)
    }

    /* Return the pin
    *  @return pin
    */
    fun pin():Int{
        return pin
    }


    //Set method
    fun setPin(newPin: Int) {
        pin = newPin
    }

}