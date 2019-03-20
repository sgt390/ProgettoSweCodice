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
* Gian Marco Bratzu     || 2019-03-18   || Writing class ConnectorReadEmail
*      ||    || Verifying code
*/

package com.megalexa.adapters.connectors
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException
import java.io.InputStream
import java.net.MalformedURLException
import java.net.URL
import java.net.URLConnection
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.concurrent.thread


class ConnectorReadEmail(private var city: String):Connector {
    private var result= AtomicBoolean(false)
    init {
       city ="Padova";

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