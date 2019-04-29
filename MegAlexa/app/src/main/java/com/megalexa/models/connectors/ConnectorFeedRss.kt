/*
* File: ConnectorFeedRss.kt
* Version: 1.0.0
* Date: 2019-02-16
* Author: Ludovico Brocca
*         Matteo Depascale
* License:
*
* History:
* Author                || Date         || Description
* Ludovico Brocca       || 2019-02-16   || Writing class ConnectorFeedRss
* Matteo Depascale      || 2019-02-23   || Verifying code
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


class ConnectorFeedRss(private var url: String):Connector {
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

    /**
     * A ConnectorFeedRss is valid if the feedMessage is correct
     * @return feed is valid
     */
    override fun valid():Boolean {

        val operation = doAsyncResult {
            return@doAsyncResult isRssFeed()
        }
        return operation.get()
    }

    /**
     * isRssFeed checks if the content received from the URL returns a correct rss feed in xml format
     * @returns true if the url is an rssFeed
     */
    private fun isRssFeed() :Boolean {


        var resource: URL
        var xpp: XmlPullParser
        var iStream: InputStream
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

    /**getInputStream(resource) returns an InputStream for the given URL
     * @param URL url that needs th InputStream
     */
    private fun getInputStream(resource: URL) : InputStream {

        try {
            return resource.openConnection().getInputStream()
        }catch (err: IOException){
            err.printStackTrace()
        }
        return resource.openConnection().getInputStream()
    }

    fun printConnectionResult()= url
}