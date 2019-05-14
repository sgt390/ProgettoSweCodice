/*
 *
 *  File name: MegAlexaService.kt
 *  Version: 1.0.0
 *  Date: 2019-03-01
 *  Author: Ludovico Brocca
 *  License:
 *  History:
 *  Author            || Date            || Description
 *  Ludovico Brocca   || 2019-03-01      || File created
 *  Gian Marco Bratzu || 2019-03-10      || Verifying code
 * /
 *
 */

package com.megalexa.util.service

import com.megalexa.models.MegAlexa
import com.megalexa.models.workflow.Workflow
import org.json.JSONObject

object MegAlexaService:Service() {

    override val resource: String
        get() = "all"


    override fun convertFromJSON(jsonObject: JSONObject): MegAlexa {

        val jsonUser=JSONObject()
        val jsonWorkflow=JSONObject()

        jsonUser.put("userID",jsonObject.get("userID"))
        jsonUser.put("name",jsonObject.get("name"))
        jsonUser.put("email",jsonObject.get("email"))

        jsonWorkflow.put("workflowList","workflowList")

        val user= UserService.convertFromJSON(jsonUser)
        val workflows=ArrayList<Workflow>()

        val keys = jsonObject.getJSONObject("workflowList").keys()
        for (item in keys){
            val workflow = JSONObject()
            workflow.put("workflowName", item)
            workflow.put("workflow", jsonObject.getJSONObject("workflowList").getJSONArray(item))
            workflows.add(WorkflowService.convertFromJSON(workflow))
        }

        return MegAlexa.workflows(workflows).user(user).build()
    }

    override fun <MegAlexa> convertToJSON(t: MegAlexa): JSONObject {
        return JSONObject()
    }
}