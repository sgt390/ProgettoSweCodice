/*
* File: ConnectorAmazonMusic.kt
* Version: 1.0.0
* Date: 2019-03-28
* Author: Andrea Deidda
*
* License:
*
* History:
* Author                || Date         || Description
* Andrea Deidda         || 2019-03-28   || Writing class ConnectorAmazonMusic
*
*/

package com.megalexa.models.connectors

import java.util.concurrent.atomic.AtomicBoolean

class ConnectorAmazonMusic(private var account: String):Connector {
    private var result= AtomicBoolean(false)
    init {
        account ="someAccount";

        //   TODO

    }


    override fun connect(url: String):String {

        return "stringa";

        //  TODO
    }


    override fun valid():Boolean {

        return true;
//      TODO
    }

}