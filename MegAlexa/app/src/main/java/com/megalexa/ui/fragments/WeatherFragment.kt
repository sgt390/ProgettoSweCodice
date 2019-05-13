package com.megalexa.ui.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.megalexa.R
import com.megalexa.models.connectors.ConnectorWeather
import com.megalexa.ui.activities.CreateBlockActivity

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

