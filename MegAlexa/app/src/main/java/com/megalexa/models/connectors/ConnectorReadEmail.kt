/*
* File: ConnectorReadMail.kt
* Version: 1.0.0
* Date: 2019-03-18
* Author: Gian Marco Bratzu
*
* License:
*
* History:
* Author                || Date         || Description
* Gian Marco Bratzu     || 2019-03-18   || Writing class ConnectorReadEmail
*      ||    || Verifying code
*/

package com.megalexa.models.connectors

import java.util.concurrent.atomic.AtomicBoolean


class ConnectorReadEmail(private var email: String, private var password: String):Connector {
    private var result= AtomicBoolean(false)
    init {
       email ="qualcosa@gmail.com"
    }


    override fun connect(url: String):String {
        return "stringa"
    }


    override fun valid():Boolean {
        return true;
    }

}