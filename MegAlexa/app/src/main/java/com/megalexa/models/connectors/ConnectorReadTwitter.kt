/*
* File: ConnectorFeedRss.kt
* Version: 1.0.0
* Date: 2019-03-18
* Author: Gian Marco Bratzu
*
* License:
*
* History:
* Author                || Date         || Description
* Gian Marco Bratzu     || 2019-03-18   || Writing class ConnectorReadTwitter
*      ||    || Verifying code
*/

package com.megalexa.models.connectors

import java.util.concurrent.atomic.AtomicBoolean

class ConnectorReadTwitter(private var mail: String, private var password: String):Connector {
    private var result= AtomicBoolean(false)
    init {
        mail ="someAccount";

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