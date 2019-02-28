package com.megalexa.util

import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.URL
import javax.net.ssl.HttpsURLConnection

object GatewayRequests{

    private const val api_URL= "https://m95485wij9.execute-api.us-east-1.amazonaws.com/beta/"


    fun getJSONFile() : JSONObject {

        TODO()
    }

        ////////POST Requests Functions
    private fun postRequestToWrite(jSon_object: JSONObject, url: String){

           val myURL = URL(url)
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
                   println("Response : $response")
               }


           }



    }

    private fun postRequestToRead(jSon_object: JSONObject, url: String) :JSONObject {
        val myURL = URL(url)


        with(myURL.openConnection() as HttpsURLConnection){
            setRequestProperty("Content-Type", "application/json")
            requestMethod = "POST"
            doOutput = true
            val wr = OutputStreamWriter(outputStream)
            wr.write(jSon_object.toString())
            wr.flush()
            var readResult: StringBuffer
            BufferedReader(InputStreamReader(inputStream)).use {
                readResult = StringBuffer()
                var inputLine = it.readLine()
                while (inputLine != null) {
                    readResult.append(inputLine)
                    inputLine = it.readLine()
                }


                return JSONObject(readResult.toString())
            }


        }
    }



    ////////////USER FUNCTIONS
    fun saveUser(paramID :String ,paramName:String, paramMail:String){
        val jSonObject= JSONObject()
        val resources="user/create"
        jSonObject.put("userID", paramID)
        jSonObject.put("name", paramName)
        jSonObject.put("email", paramMail)

        postRequestToWrite(jSonObject, api_URL+resources)

    }


    fun readUser(paramID: String) : JSONObject{
        val jSonObject= JSONObject()
        val resources="user/read"
        jSonObject.put("userID",paramID)
        val response =postRequestToRead(jSonObject, api_URL+resources)
        return response

    }
    //////////BLOCK FUNCTIONS
    fun saveBlock() {
        TODO()

    }

    fun deleteBlock() {

        TODO()
    }

    fun updateBlock() {

        TODO()
    }

    ///////////WORKFLOW FUNCTIONS
    fun saveWorkflow(workflow_name: String) {

        TODO()
    }


    fun deleteWorkflow(workflow_name: String) {

        TODO()

    }

    fun updateWorkflow() {

        TODO()
    }


}