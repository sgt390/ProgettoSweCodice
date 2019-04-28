/*
* File: BlockWeather.kt
* Version: 1.0.0
* Date:
* Author: Gian Marco Bratzu
* License:
*
* History:
* Author                || Date         || Description
* Gian Marco Bratzu     ||    || Writing class BlockWeather

*/

package com.megalexa.models.blocks

import android.util.Log
import com.megalexa.R
import com.megalexa.models.connectors.Connector
import com.megalexa.models.connectors.ConnectorWeather
import com.megalexa.util.ApplicationContextProvider
import org.json.JSONObject


class BlockWeather(private val OpenWeatherObj : JSONObject): Block {


    private val APIKey = ApplicationContextProvider.context!!.getResources()!!.getString(R.string.OpenWeather_API_Key)


    /** getInformation()
     * @return  String that sums up the information for the given weather
     *
     */
    override fun getInformation():String {
        Log.d("oggettoJson",OpenWeatherObj.toString())
        return "Weather "
    }

    override fun toJSON() : JSONObject {
        val allBlock = JSONObject()
        allBlock.put("blockType", "Weather" )
        val config = JSONObject()
        config.put("APIKey", getAPIKey())
        config.put("Latitude", getLatitude())
        config.put("Longitude", getLongitude())
        allBlock.put("config", config)
        return allBlock
    }

        fun getAPIKey(): String? {
            return APIKey
        }

        fun getLatitude(): String? {
            return OpenWeatherObj.getString("Latitude")
        }

        fun getLongitude(): String? {
            return OpenWeatherObj.getString("Longitude")
        }
    }

