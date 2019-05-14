/*
* File: BlockWeather.kt
* Version: 1.0.0
* Date: 2019-03-02
* Author: Gian Marco Bratzu
* License:
*
* History:
* Author                || Date         || Description
* Gian Marco Bratzu     || 2019-03-02   || Writing class BlockWeather
* Mirko Franco          || 2019-03-10   || Verifying code

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

