package com.megalexa.models.blocks

import android.location.Location


class BlockWeather(private val location: Location) :Block{

    private var longitude= location.longitude
    private var latitude=location.latitude


    override fun getInformation(): String {
        return "longitude:  $longitude latitude : $latitude"
    }


    fun getLongitude() :Double {
        return longitude
    }

    fun getLatitude(): Double {
        return latitude
    }
}