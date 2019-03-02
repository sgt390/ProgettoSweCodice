package com.megalexa.models

import com.megalexa.models.workflow.Workflow
import com.megalexa.util.GatewayRequests


class MegAlexa {

    private var workflows  = ArrayList<Workflow>()
    private  lateinit var user : User

    fun saveUser(user: User){
        this.user = user
        GatewayRequests.saveUser(user)
    }

    fun addWorkflow(w: Workflow){
        workflows.add(w)
    }

    fun getWorkflowList(): ArrayList<Workflow> {

        return workflows
    }

    fun setUser(user: User){
        this.user = user
    }

    fun getUser() : User{
        return user
    }

    fun loadWorkflow() : ArrayList<Workflow>{
        workflows = GatewayRequests.readWorkflow(user)
        return workflows
    }

    fun isPresentWorkflow(w: String) : Boolean{
        var isPresent  = false
        for(workflow in workflows){
            if(workflow.getName() == w){
                isPresent = true
            }
        }
        return isPresent
    }

}