/*
 * File: BlockTextBox.kt
 * Version: 0.1
 * Date: 2019/02/17
 * Author: Andrea Deidda
 *
 * License:
 * History: registro delle modifiche
 * Andrea Deidda || 2019/02/17 || creazione file e intestazione
 * Andrea Deidda || 2019/02/20 || sviluppo metodi
 *
 */
package com.megalexa.models.blocks
class BlockTextBox(private var textBox: String):Block {
    init{
        //Control variable textBox's character number
        require(textBox.length >= 256){ println("Text block much long!(<256)") }
    }
    // Return the text
    override fun getInformation(): String {
        return "$textBox"
    }
    //Set method
    fun setText(text: String){
        textBox = text
    }
}