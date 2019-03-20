package com.megalexa.util.service

import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.URL
import javax.net.ssl.HttpsURLConnection

abstract class Service {

    protected val api_Url = "https://m95485wij9.execute-api.us-east-1.amazonaws.com/beta/"

    abstract fun getOperation(jsonObject: JSONObject):JSONObject

    abstract fun putOperation(jsonObject: JSONObject):JSONObject

    abstract fun deleteOperation(jsonObject: JSONObject):JSONObject


    fun postOperation(jsonObject: JSONObject,url:String) {

        val myURL = URL(url)
        with(myURL.openConnection() as HttpsURLConnection){
            setRequestProperty("Content-Type", "application/json")
            requestMethod = "POST"
            doOutput = true
            val wr = OutputStreamWriter(outputStream)
            wr.write(jsonObject.toString())
            wr.flush()
            println("URL : $url")
            println("Response Code : $responseCode")
            BufferedReader(InputStreamReader(inputStream)).use {
                val response = StringBuffer()
                var inputLine = it.readLine()
                while (inputLine != null) {
                    response.append(inputLine)
                    inputLine = it.readLine()
                }
                println("Response : $response")
            }

        }


    }

}