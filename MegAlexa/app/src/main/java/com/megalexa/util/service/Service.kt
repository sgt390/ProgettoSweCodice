/*
 *
 *  File name:
 *  Version:
 *  Date:
 *  Author:
 *  License:
 *  History:
 *  Author        || Date            || Description
 * /
 *
 */

package com.megalexa.util.service

import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.URL
import java.net.URLEncoder
import javax.net.ssl.HttpsURLConnection

abstract class Service : JSONConverter{

    private var APIUrl = "https://m95485wij9.execute-api.us-east-1.amazonaws.com/beta/"

    abstract val resource:String

    open fun getOperation(params:List<Pair<String,String>>): JSONObject {
        var json= JSONObject()
        val query = StringBuilder()
        for (item in params) {
            query.append(URLEncoder.encode(item.first,"UTF-8")+"="+URLEncoder.encode(item.second,"UTF-8")+ "&")
        }
        val string=query.substring(0,query.length-1)
        val url= "$APIUrl$resource/"
        val myURL = URL("$url?$string")
        with(myURL.openConnection() as HttpsURLConnection) {
            setRequestProperty("Content-Type","application/json")
            requestMethod= "GET"
            println("URL : $url")
            println("Response Code : $responseCode")
            BufferedReader(InputStreamReader(inputStream)).use {
                val response = StringBuffer()
                var inputLine = it.readLine()
                while (inputLine != null) {
                    response.append(inputLine)
                    inputLine = it.readLine()
                }
                json= JSONObject(response.toString())
            }
        }
        return json
    }

    open fun putOperation(jsonObject: JSONObject) : String{
        val response= StringBuffer()
        val url= "$APIUrl$resource"
        val myURL = URL(url)
        with(myURL.openConnection() as HttpsURLConnection) {
            setRequestProperty("Content-Type", "application/json")
            requestMethod = "PUT"
            doOutput = true
            val wr = OutputStreamWriter(outputStream)
            wr.write(jsonObject.toString())
            wr.flush()
            println("URL : $url")
            println("Response Code : $responseCode")
            BufferedReader(InputStreamReader(inputStream)).use {
                var inputLine = it.readLine()
                while (inputLine != null) {
                    response.append(inputLine)
                    inputLine = it.readLine()
                }

            }
        }
        return response.toString()
    }

    open fun deleteOperation(params:List<Pair<String,String>>) :String {
        val response = StringBuffer()
        val query = StringBuilder()
        for (item in params) {
            query.append(URLEncoder.encode(item.first,"UTF-8")+"="+URLEncoder.encode(item.second,"UTF-8")+ "&")
        }
        val string=query.substring(0,query.length-1)
        val url= "$APIUrl$resource/"
        val myURL = URL("$url?$string")
        with(myURL.openConnection() as HttpsURLConnection) {
            setRequestProperty("Content-Type", "application/json")
            requestMethod= "DELETE"
            println("URL : $url")
            println("Response Code : $responseCode")
            BufferedReader(InputStreamReader(inputStream)).use {

                var inputLine = it.readLine()
                while (inputLine != null) {
                    response.append(inputLine)
                    inputLine = it.readLine()
                }
                println("Response : $response")
            }
        }
        return response.toString()
    }

    open fun postOperation(jsonObject: JSONObject):String {
        val url= "$APIUrl$resource"
        val response = StringBuffer()
        val myURL = URL(url)
        with(myURL.openConnection() as HttpsURLConnection) {
            setRequestProperty("Content-Type", "application/json")
            requestMethod = "POST"
            doOutput = true
            val wr = OutputStreamWriter(outputStream)
            wr.write(jsonObject.toString())
            wr.flush()
            println("URL : $url")
            println("Response Code : $responseCode")
            BufferedReader(InputStreamReader(inputStream)).use {
                var inputLine = it.readLine()
                while (inputLine != null) {
                    response.append(inputLine)
                    inputLine = it.readLine()
                }
                println("Response : $response")
            }

        }
        return response.toString()
    }
    fun getURL():String {
        return APIUrl
    }

    fun setURL(param : String){
        APIUrl= param
    }
}