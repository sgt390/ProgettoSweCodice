package com.megalexa.util.service

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
            workflowArray.put(block.toJSON())
        }
        workflowName.put(workflow.getName(), workflow)
        return workflowName
    }
}