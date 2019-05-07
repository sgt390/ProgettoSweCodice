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
* Gian Marco Bratzu     || 2019-03-18   || Writing class ConnectorWeather
*      ||    || Verifying code
*/

package com.megalexa.models.connectors

import com.megalexa.R
import com.megalexa.util.ApplicationContextProvider
import org.jetbrains.anko.doAsyncResult
import java.io.*
import java.net.URL
import java.net.URLEncoder
import javax.net.ssl.HttpsURLConnection


class ConnectorWeather(private var city: String):Connector {
    private val connectionResult: String
    private val BASE_URL = "http://api.openweathermap.org/data/2.5/weather?q="
    private val IMG_URL = "http://openweathermap.org/img/w/"
    private val api_key =
        "&APPID=" + ApplicationContextProvider.context!!.getResources()!!.getString(R.string.OpenWeather_API_Key)

    init {
        connectionResult = connect(city)
    }


    override fun connect(city: String): String {

        val result: String
        if (valid()) {
            result = "connection successful"
        } else {
            result = "connection refused: city is invalid"
        }
        return result
    }


    override fun valid(): Boolean{

        val operation = doAsyncResult {
            isCityValid()
        }
        return operation.get()!!
    }

    private fun isCityValid(): Boolean?{

        return getWeatherData(city)
    }

    fun printConnectionResult() = connectionResult


    fun getWeatherData(location: String): Boolean? {
        val query = StringBuilder()
        query.append(URLEncoder.encode("q", "UTF-8") + "=")
        query.append(URLEncoder.encode(location, "UTF-8"))
        query.append(
            "&" + URLEncoder.encode("APPID", "UTF-8") + "=" +
                    URLEncoder.encode("4b1ea0b33edc40ba538b366b98484801 ", "UTF-8")
        )
        val string = query.substring(0, query.length - 1)
        val url = "https://api.openweathermap.org/data/2.5/weather"
        val myURL = URL("$url?$string")

        with(myURL.openConnection() as HttpsURLConnection) {
            setRequestProperty("Content-Type", "application/json")
            requestMethod = "GET"
            try {
                BufferedReader(InputStreamReader(inputStream) as Reader?).use {
                    val response = StringBuffer()
                    var inputLine = it.readLine()
                    while (inputLine != null) {
                        response.append(inputLine)
                        inputLine = it.readLine()
                    }
            }
                return true
            }catch (e:FileNotFoundException){
             return false
            }
        }
    }
}