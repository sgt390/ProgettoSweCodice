package com.megalexa.adapters.connectors
import android.webkit.URLUtil
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException
import java.io.InputStream
import java.net.MalformedURLException
import java.net.URL
import java.net.URLConnection


class ConnectorFeedRss(private var url: String):Connector {
    init {
        url=connect(url)
    }

    override fun connect(url: String):String {

        if (valid()) {

            return url
        }else {

            return "ERROR_INVALID_URL"

        }
    }

    /**
     * A ConnectorFeedRss is valid if the feedMessage is correct
     * @return feed is valid
     */
    override fun valid():Boolean {
        return URLUtil.isHttpsUrl(url) && isRssFeed()
    }

    /**
     * isRssFeed checks if the content received from the URL returns a correct rss feed in xml format
     * @returns true if the url is an rssFeed
     */
    private fun isRssFeed(): Boolean {

        val resource:URL
        val xpp: XmlPullParser
        val iStream:InputStream

        try {

            resource = URL(url)

            val factory = XmlPullParserFactory.newInstance()

            xpp = factory.newPullParser()

            iStream = getInputStream(resource)

            iStream.use {

                x -> xpp.setInput(x, "UTF_8")
                xpp.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)
                xpp.nextTag()

                if(xpp.name =="rss")
                    return true
            }


        }catch (err: MalformedURLException) {

            err.printStackTrace()

        }catch (err: XmlPullParserException) {

            err.printStackTrace()
        }


    return false
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


}