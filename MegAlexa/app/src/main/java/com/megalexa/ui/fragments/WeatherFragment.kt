package com.megalexa.ui.fragments

import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.JsonObject
import com.megalexa.R
import com.megalexa.util.service.BlockWeatherService
import com.megalexa.util.service.BlockWeatherService.getOperation
import org.jetbrains.anko.doAsync
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.net.URLEncoder
import javax.net.ssl.HttpsURLConnection

class WeatherFragment: Fragment(){

    private var city =  ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.weather_fragment_layout,container,false)

        val button= view.findViewById<Button>(R.id.city_button)
        val editText= view.findViewById<EditText>(R.id.insert_city)

        button.setOnClickListener {
            if(editText.text.toString() == "") {
                Toast.makeText(context, "city is empty", Toast.LENGTH_SHORT).show()
            }
            else{
                city= editText.text.toString()
//                val activity= activity as CreateBlockActivity
//                activity.onFragmentClick(this)

               val prova : JSONObject = doAsync { BlockWeatherService.getOperation(city) }
//                Toast.makeText(context, prova, Toast.LENGTH_SHORT).show()
//                val prova = FromJSON(getOperation("Padova"))
//                Toast.makeText(context,prova , Toast.LENGTH_SHORT).show()
            }
        }
        return view
    }

//    fun FromJSON(jsonObject: JSONObject): String? {
//        return jsonObject.getJSONObject("coord").getString("lon")
//    }

//    fun getOperation(city : String): JSONObject {
//        var json= JSONArray()
//        val query = StringBuilder()
//        query.append(URLEncoder.encode("q=","UTF-8"))
//        query.append(URLEncoder.encode(city,"UTF-8"))
//        query.append(URLEncoder.encode("&APPID=4b1ea0b33edc40ba538b366b98484801","UTF-8"))
//        val string=query.substring(0,query.length-1)
//        val url= "api.openweathermap.org/data/2.5/weather"
//        val myURL = URL("$url?$string")
//        with(myURL.openConnection() as HttpsURLConnection) {
//            setRequestProperty("Content-Type", "application/json")
//            requestMethod= "GET"
//            println("URL : $url")
//            println("Response Code : $responseCode")
//            BufferedReader(InputStreamReader(inputStream)).use {
//                val response = StringBuffer()
//                var inputLine = it.readLine()
//                while (inputLine != null) {
//                    response.append(inputLine)
//                    inputLine = it.readLine()
//                }
//                json= JSONArray(response.toString())
//            }
//        }
//        return JSONObject().put("content",json)
//    }


    fun getText(): String{
        return city
    }

}

