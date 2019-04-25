package com.megalexa.util.service


import com.megalexa.models.blocks.BlockWeather
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.net.URLEncoder
import javax.net.ssl.HttpsURLConnection

object BlockWeatherService :BlockService() {

    override fun convertFromJSON(jsonObject: JSONObject): BlockWeather {
        TODO()
    }

    override fun <T> convertToJSON(t: T): JSONObject {
        TODO()
    }

    override fun getOperation(params: List<Pair<String, String>>): JSONObject {
        var json= JSONArray()
        val query = StringBuilder()
        for (item in params) {
            query.append(URLEncoder.encode(item.first,"UTF-8")+"="+ URLEncoder.encode(item.second,"UTF-8")+ "&")
        }
        val string=query.substring(0,query.length-1)
        val url= "api.openweathermap.org/data/2.5/weather?q="
        val myURL = URL("$url?$string")
        with(myURL.openConnection() as HttpsURLConnection) {
            setRequestProperty("Content-Type", "application/json")
            requestMethod= "GET"
            println("URL : $url")
            println("Response Code : $responseCode")
            BufferedReader(InputStreamReader(inputStream)).use {
                val response = StringBuffer()
                var inputLine = it.readLine()
                while (inputLine != null) {
                    response.append(inputLine)
                    inputLine = it.readLine()
                }
                json= JSONArray(response.toString())
            }
        }
        return JSONObject().put("content",json)
    }
}