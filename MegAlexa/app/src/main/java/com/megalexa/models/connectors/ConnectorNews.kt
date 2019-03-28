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
* Gian Marco Bratzu     || 2019-03-19   || Writing class ConnectorNews
*      ||    || Verifying code
*/

package com.megalexa.models.connectors

import java.util.concurrent.atomic.AtomicBoolean


class ConnectorNews(private var webSite: String):Connector {
    private var result= AtomicBoolean(false)
    init {
        webSite ="someSite";

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