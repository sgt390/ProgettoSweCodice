package com.megalexa.util.service

import com.megalexa.models.workflow.Workflow
import org.json.JSONArray
import org.json.JSONObject

object WorkflowService: Service() {

    override val resource: String
        get() = "workflow"

    fun convertFromJSON(jsonObject: JSONObject): Workflow {
        TODO()
    }

    fun convertToJSON(workflow: Workflow):JSONObject {
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