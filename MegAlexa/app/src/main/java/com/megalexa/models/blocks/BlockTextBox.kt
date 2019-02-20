/*
 * File: BlockTextBox.kt
 * Version: 0.1
 * Date: 2019/02/20
 * Author: Gian Marco Bratzu
 *
 * License:
 * History: registro delle modifiche
 * Gian Marco Bratzu || data || creazione file e intestazione
 * Andrea Deidda || 2019/02/20 || sviluppo metodi
 *
 */
package com.megalexa.models.blocks
class BlockTextBox(private val textBox: String):Block {
    init{
        //Control variable textBox's character number
        require(textBox.length >= 500){ println("Text block much long!(<500)") }
    }
    override fun getInformation(): String {
        return "$textBox" //To change body of created functions use File | Settings | File Templates.
    }
}