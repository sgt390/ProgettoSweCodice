package com.megalexa.util.service


import android.util.Log
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

     fun getOperation(city : String): JSONObject {
        var json= JSONArray()
        val query = StringBuilder()
        query.append(URLEncoder.encode("q","UTF-8")+ "=")
        query.append(URLEncoder.encode(city,"UTF-8"))
        query.append("&" + URLEncoder.encode("APPID","UTF-8")+ "=" +
                URLEncoder.encode("4b1ea0b33edc40ba538b366b98484801 ","UTF-8"))
        val string=query.substring(0,query.length-1)
        val url= "https://api.openweathermap.org/data/2.5/weather"
        val myURL = URL("$url?$string")
        // Log.d("questoUrl",myURL.toString())
            with(myURL.openConnection() as HttpsURLConnection) {
            setRequestProperty("Content-Type", "application/json")
            requestMethod= "GET"
          //  println("URL : $url")
          //  println("Response Code : $responseCode")
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