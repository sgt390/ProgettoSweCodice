package com.megalexa.util.service


import android.speech.tts.TextToSpeech
import com.megalexa.models.MegAlexa
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
        var workflow : Workflow = Workflow(jsonObject.get("workflowName").toString())
        var blocks : JSONArray = jsonObject.getJSONArray("workflow")
        for (i in 0.. (blocks.length() - 1)){
            workflow.addBlock(
                when(blocks.getJSONObject(i).get("blockType")){
                    "TextToSpeech" -> BlockTextToSpeechService.convertFromJSON(blocks.getJSONObject(i))
                    "Stock" -> BlockBorsaService.convertFromJSON(blocks.getJSONObject(i))
                    "Crypto" -> BlockCryptoService.convertFromJSON(blocks.getJSONObject(i))
                    "FeedRSS" -> BlockFeedRssService.convertFromJSON(blocks.getJSONObject(i))
                    "News" -> BlockNewsService.convertFromJSON(blocks.getJSONObject(i))
                    "PIN" -> BlockPinService.convertFromJSON(blocks.getJSONObject(i))
                    "Email" -> BlockReadEmailService.convertFromJSON(blocks.getJSONObject(i))
                    "Sport" -> BlockSportService.convertFromJSON(blocks.getJSONObject(i))
                    "Twitter" -> BlockTwitterService.convertFromJSON(blocks.getJSONObject(i))
                    "Weather" -> BlockWeatherService.convertFromJSON(blocks.getJSONObject(i))
                    "Filter" -> FilterService.convertFromJSON(blocks.getJSONObject(i))
                    "Calendar" -> FilterService.convertFromJSON(blocks.getJSONObject(i))
                    "List" -> BlockListService.convertFromJSON(blocks.getJSONObject(i))
                    else -> BlockTextToSpeech("Undefined")
                }
            )
        }
        return workflow
    }

    override fun <Workflow> convertToJSON(t: Workflow): JSONObject {
        val workflow= t as com.megalexa.models.workflow.Workflow
        val jsonArray = JSONArray()
        val blocklist = workflow.getBlocks()
        for(block in blocklist) {
            jsonArray.put(convertBlock(block))
        }
        val jsonObject= JSONObject()
        jsonObject.put("userID", MegAlexa.getInstance().getUser().getID())
        jsonObject.put("workflowName", workflow.getName().toLowerCase().replace("\\s".toRegex(), ""))
        jsonObject.put("workflow",jsonArray)
        return jsonObject
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

            is BlockTwitter -> result =BlockTwitterService.convertToJSON(block)

            is BlockTwitterHashtag -> result = BlockTwitterHashtagService.convertToJSON(block)

            is BlockTwitterHomeTL -> result = BlockTwitterHomeTLService.convertToJSON(block)

            is BlockTwitterWrite -> result = BlockWriteTweetService.convertToJSON(block)

            is BlockWeather -> result =BlockWeatherService.convertToJSON(block)

            is BlockList -> result =BlockListService.convertToJSON(block)
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
