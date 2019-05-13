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

import com.megalexa.R
import com.megalexa.util.ApplicationContextProvider
import org.json.JSONObject


class BlockWeather(private val openWeatherObj : JSONObject): Block {


    private val APIKey = ApplicationContextProvider.context!!.getResources()!!.getString(R.string.OpenWeather_API_Key)


    /** getInformation()
     * @return  String that sums up the information for the given weather
     *
     */
    override fun getInformation():String {
        return ApplicationContextProvider.context!!.resources!!.getString(R.string.BlockWeather)
    }

        fun getAPIKey(): String? {
            return APIKey
        }

        fun getLatitude(): String? {
            return openWeatherObj.getString("Latitude")
        }

        fun getLongitude(): String? {
            return openWeatherObj.getString("Longitude")
        }
}

