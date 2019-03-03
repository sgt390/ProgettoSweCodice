package com.megalexa.util

import android.util.Log
import com.google.gson.JsonObject
import com.megalexa.models.User
import com.megalexa.models.blocks.Block
import com.megalexa.models.blocks.BlockFeedRss
import com.megalexa.models.blocks.BlockTextBox
import com.megalexa.models.workflow.Workflow
import org.json.JSONArray
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


    private fun postRequestToReadArray(jSon_object: JSONObject, url: String) :JSONArray {
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

                return JSONArray(readResult.toString())

            }


        }
    }


    ////////////USER FUNCTIONS
    fun saveUser(user: User){
        val resources="user/create"
        postRequestToWrite(user.toJSON(), api_URL+resources)

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
    fun saveWorkflow(user: User, w: Workflow) {
        var workflow = JSONObject()
        workflow.put("userID", user.getID())
        workflow.put("workflowName", w.getName())
        var workflowContent = JSONArray(w.getBlocks(user))
        workflow.put("workflow", workflowContent)
        postRequestToWrite(workflow, api_URL + "workflow/save")
    }


    fun deleteWorkflow(workflow_name: String) {

        TODO()

    }

    fun updateWorkflow() {

        TODO()
    }



    fun readWorkflow(user : User) : ArrayList<Workflow>{
        val listWorkflow : ArrayList<Workflow> = ArrayList<Workflow>()
        val userID = JSONObject()
        userID.put("userID", user.getID())
        val resources="workflow/read"
        val workflowListJSON = postRequestToRead(userID, api_URL + resources )

        for(workflow in workflowListJSON.keys()){
            listWorkflow.add(Workflow(workflow))
        }
        return listWorkflow
    }

    fun readBlocks(user: User, workflow: Workflow) : ArrayList<Block>?{
        val blocksList : ArrayList<Block> = ArrayList<Block>()
        val toPass = JSONObject()
        toPass.put("userID", user.getID())
        toPass.put("workflow", workflow.getName())
        val resources = "block/read"
        val blocks = postRequestToReadArray(toPass, api_URL + resources)
        for(i in 0..blocks.length()-1){
            val item = blocks.getJSONObject(i).getString("blockType")

            when(item){

                "textToSpeech"-> blocksList.add(BlockTextBox("Hi, there is a test"))

                "FeedRSS" -> blocksList.add(BlockFeedRss("https://feedforall.com/sample.xml"))
                //MORE BLOCKS TO BE ADDED
            }
        }
        //blocksList.add(BlockTextBox("Hi, there is a test"));

        return blocksList
    }



}