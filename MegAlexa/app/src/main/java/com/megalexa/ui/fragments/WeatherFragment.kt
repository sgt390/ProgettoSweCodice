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
import com.megalexa.models.connectors.ConnectorWeather
import com.megalexa.ui.activities.CreateBlockActivity
import com.megalexa.util.service.BlockWeatherService
import com.megalexa.util.service.BlockWeatherService.getOperation
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.doAsyncResult
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.net.URLEncoder
import java.util.concurrent.Future
import javax.net.ssl.HttpsURLConnection

class WeatherFragment: Fragment(){

    private var city =  ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.weather_fragment_layout,container,false)

        val button= view.findViewById<Button>(R.id.city_button)
        val editText= view.findViewById<EditText>(R.id.insert_city)

        button.setOnClickListener {
            city = editText.text.toString()
            val isValid=ConnectorWeather(city).valid()
            if(editText.text.toString() == "" || !isValid) {
                Toast.makeText(context, "city is not valid", Toast.LENGTH_SHORT).show()
            }
            else{
                city = editText.text.toString()
                val activity= activity as CreateBlockActivity
                activity.onFragmentClick(this)
            }
        }
        return view
    }

    fun getCity()= city
}

