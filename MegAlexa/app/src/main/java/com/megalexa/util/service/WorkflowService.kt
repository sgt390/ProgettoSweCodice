package com.megalexa.util.service


import com.megalexa.models.blocks.*
import com.megalexa.models.workflow.Workflow
import org.json.JSONArray
import org.json.JSONObject

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
        when(block){

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
}