package com.megalexa.util.service



import android.util.Log
import com.megalexa.models.blocks.BlockWeather
import org.jetbrains.anko.doAsyncResult
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.net.URLEncoder
import javax.net.ssl.HttpsURLConnection

object BlockWeatherService :BlockService() {


    override fun convertFromJSON(jsonObject: JSONObject): BlockWeather {
        return BlockWeather(jsonObject.getJSONObject("config"))
    }


    override fun <BlockWeather> convertToJSON(t: BlockWeather): JSONObject {
        val blockWeather = t as com.megalexa.models.blocks.BlockWeather
        val allBlock = JSONObject()
        allBlock.put("blockType", "Weather")
        val config = JSONObject()
        config.put("APIKey", blockWeather.getAPIKey())
        config.put("Latitude", blockWeather.getLatitude())
        config.put("Longitude", blockWeather.getLongitude())
        allBlock.put("config", config)
        return allBlock
    }


     fun getOperation(city : String): JSONObject {
        var json= JSONObject()
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
            BufferedReader(InputStreamReader(inputStream)).use {
                val response = StringBuffer()
                var inputLine = it.readLine()

                while (inputLine != null) {
                    response.append(inputLine)
                    inputLine = it.readLine()
                }
                json.put("Latitude",JSONObject(response.toString()).getJSONObject("coord").get("lat"))
                json.put("Longitude",JSONObject(response.toString()).getJSONObject("coord").get("lon"))
               }
        }
        return json
    }

}