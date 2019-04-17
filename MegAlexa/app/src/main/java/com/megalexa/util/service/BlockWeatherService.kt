package com.megalexa.util.service

import com.megalexa.models.blocks.BlockSport
import org.json.JSONObject

object BlockWeatherService :BlockService() {

    override fun convertFromJSON(jsonObject: JSONObject): BlockSport { TODO() }

    override fun <T> convertToJSON(t: T): JSONObject { TODO() }

    /*
    override fun convertFromJSON(jsonObject: JSONObject): BlockWeather {
        val ApiKey = json.getJSONObject("config").getString("APIKey")
        val lat = json.getJSONObject("config").getString("Latitude")
        val lon = json.getJSONObject("config").getString("Longitude")
        return BlockWeather(ApiKey,lat,lon)
    }

    override fun <BlockWeather> convertToJSON(t: BlockWeather): JSONObject {
        val blockWeather = t as com.megalexa.models.blocks.BlockWeather
        val allBlock = JSONObject()
        allBlock.put("blockType", "Weather")
        val config = JSONObject()
        config.put("APIKey", blockWeather.getApiKey())
        config.put("Latitude", blockWeather.getLatitude())
        config.put("Longitude", blockWeather.getLongitude())
        allBlock.put("config", config)
        return allBlock
    }
    */
}