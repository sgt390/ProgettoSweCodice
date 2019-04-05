package com.megalexa.util.service


import com.megalexa.models.blocks.*
import com.megalexa.models.workflow.Workflow
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.net.URLEncoder
import javax.net.ssl.HttpsURLConnection

object WorkflowService: Service() {

    override val resource: String
        get() = "workflow"

    override fun convertFromJSON(jsonObject: JSONObject): Workflow {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <Workflow> convertToJSON(t: Workflow): JSONObject {
        val workflow= t as com.megalexa.models.workflow.Workflow
        val workflowName = JSONObject()
        val workflowArray = JSONArray()
        val blocklist= workflow.getBlocks()
        for(block in blocklist) {
            workflowArray.put(convertBlock(block))
        }
        workflowName.put(workflow.getName(), workflow)
        return workflowName
    }
    private fun convertBlock(block: Block):JSONObject{
        var result=JSONObject()
        when(block) {

            is BlockTextToSpeech -> result=BlockTextToSpeechService.convertToJSON(block)

            is BlockFeedRss -> result=BlockFeedRssService.convertToJSON(block)

            is BlockBorsa -> result=BlockBorsaService.convertToJSON(block)

            is BlockCrypto -> result=BlockCryptoService.convertToJSON(block)

            is BlockPin -> result=BlockPinService.convertToJSON(block)

            is BlockSport -> result=BlockSportService.convertToJSON(block)

            is Filter ->result= FilterService.convertToJSON(block)

            is BlockNews -> result=BlockNewsService.convertToJSON(block)

            is BlockCalendar -> result= BlockCalendarService.convertToJSON(block)

            is BlockReadEmail -> result =BlockReadEmailService.convertToJSON(block)

        }
        return result
    }

    override fun getOperation(params:List<Pair<String,String>>):JSONObject {

        var json= JSONArray()
        val query = StringBuilder()
        for (item in params) {
            query.append(URLEncoder.encode(item.first,"UTF-8")+"="+ URLEncoder.encode(item.second,"UTF-8")+ "&")
        }
        val string=query.substring(0,query.length-1)
        val url= "${getURL()}$resource/"
        val myURL = URL("$url?$string")
        with(myURL.openConnection() as HttpsURLConnection) {
            setRequestProperty("Content-Type", "application/json")
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
                json= JSONArray(response.toString())
            }
        }
        return JSONObject().put("content",json)
    }
}