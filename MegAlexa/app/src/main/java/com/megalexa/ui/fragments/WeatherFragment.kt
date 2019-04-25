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
                val prova = FromJSON(getOperation())
                Toast.makeText(context,prova , Toast.LENGTH_SHORT).show()
            }

        }


        return view
    }

     fun FromJSON(jsonObject: JSONObject): String? {
        return jsonObject.getJSONObject("coord").getString("lon")
    }
    fun getOperation(): JSONObject {


        val api ="api.openweathermap.org/data/2.5/weather?q="
        var url = api + city + "&APPID=4b1ea0b33edc40ba538b366b98484801"




            var json= JSONArray()

            //  val url= "api.openweathermap.org/data/2.5/weather?q="
            val myURL = URL("$url")
            with(myURL.openConnection() as HttpsURLConnection) {
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
            }
            return JSONObject().put("content",json)
        }
//        val queue = Volley.newRequestQueue(context)
//        val url = "https://api.openweathermap.org/data/2.5/weather?q=Padova&APPID=4b1ea0b33edc40ba538b366b98484801"
//
//// Request a string response from the provided URL.
//        val stringRequest = StringRequest(Request.Method.GET, url,
//            Response.Listener<String> { response ->
//                // Display the first 500 characters of the response string.
//                 Toast.makeText(context,"Response is: ${response.substring(0, 500)}",Toast.LENGTH_LONG).show()
//            },
//            Response.ErrorListener { Toast.makeText(context,"error",Toast.LENGTH_LONG).show() })
//
//// Add the request to the RequestQueue.
//        queue.add(stringRequest)
//
//    }

    fun getText(): String{
        return city
    }

}

