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

        val keys= jsonWorkflow.keys()

        while(keys.hasNext()) {
            val key= keys.next()

            if(jsonObject.get(key) is JSONObject) {
                val toAdd= JSONObject()
                toAdd.put(key,jsonObject.get(key))
                workflows.add(WorkflowService.convertFromJSON(toAdd))
            }
        }
        
        return MegAlexa.workflows(workflows).user(user).build()
    }

    override fun <MegAlexa> convertToJSON(t: MegAlexa): JSONObject {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}