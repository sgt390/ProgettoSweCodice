/*
* File: ConnectorSport.kt
* Version: 1.0.0
* Date: 2019-03-18
* Author: Gian Marco Bratzu
*
* License:
*
* History:
* Author                || Date         || Description
* Gian Marco Bratzu     || 2019-03-19   || Writing class ConnectorSport
*      ||    || Verifying code
*/

package com.megalexa.models.connectors
import org.jetbrains.anko.doAsyncResult
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException
import java.io.InputStream
import java.net.MalformedURLException
import java.net.URL
import java.util.concurrent.atomic.AtomicBoolean


class ConnectorSport(private var url: String):Connector {
    private val connectionResult:String
    init {
        connectionResult =connect(url)
    }


    override fun connect(url: String):String {
        val result:String

        if(valid()) {
        result = "connection successful"
            }
            else {
                result = "connection refused: url is invalid"
            }
        return result
    }

override fun valid():Boolean {
    val operation = doAsyncResult {
        isRssFeed()
    }

    return operation.get()
}
private fun isRssFeed() :Boolean {

    val resource: URL
    val xpp: XmlPullParser
    val iStream: InputStream
    val result = AtomicBoolean()
    result.set(false)
    try {
        resource = URL(url)
        val factory = XmlPullParserFactory.newInstance()
        xpp = factory.newPullParser()
        iStream = resource.openConnection().getInputStream()
        iStream.use { x ->
            xpp.setInput(x, "UTF_8")
            xpp.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)
            xpp.nextTag()
            if (xpp.name == "rss") {
                result.set(true)
            }
        }

    } catch (err: MalformedURLException) {
        err.printStackTrace()
    } catch (err: XmlPullParserException) {
        err.printStackTrace()
    }
    return result.get()

}

private fun getInputStream(resource: URL) : InputStream {

    try {
        return resource.openConnection().getInputStream()
    }catch (err: IOException){
        err.printStackTrace()
    }
    return resource.openConnection().getInputStream()
}

}