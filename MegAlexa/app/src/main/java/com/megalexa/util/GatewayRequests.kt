package com.megalexa.util

import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.URL
import javax.net.ssl.HttpsURLConnection

object GatewayRequests{

    private val api_URL= "https://m95485wij9.execute-api.us-east-1.amazonaws.com/beta/user/create"
    private var jSon_object= JSONObject()


    fun saveUser(paramID :String ,paramName:String, paramMail:String){
        jSon_object.put("userID", paramID)
        jSon_object.put("name", paramName)
        jSon_object.put("email", paramMail)

        postRequestToWrite()
        //clearing object
        jSon_object= JSONObject()

    }
     
       private fun postRequestToWrite(){

           var myURL = URL(api_URL)
           with(myURL.openConnection() as HttpsURLConnection){
               setRequestProperty("Content-Type", "application/json")
               requestMethod = "POST"
               doOutput = true
               val wr = OutputStreamWriter(outputStream)
               wr.write(jSon_object.toString())
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
                   it.close()
                   println("Response : $response")
               }


           }
    }

}