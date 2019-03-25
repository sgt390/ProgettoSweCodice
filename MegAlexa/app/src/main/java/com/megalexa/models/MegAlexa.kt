package com.megalexa.models

import android.arch.lifecycle.MutableLiveData
import com.megalexa.models.blocks.Block
import com.megalexa.models.workflow.Workflow
import com.megalexa.util.GatewayRequests


class MegAlexa {

    private var workflows= ArrayList<Workflow>()
    private var liveWorkflows= MutableLiveData<List<Workflow>>()
    private  lateinit var user : User

    init {
        liveWorkflows.value=workflows
    }

    //TODO() SAFELY DELETE THIS FUNCTION
    fun saveUser(user: User) {
        this.user = user
        //GatewayRequests.saveUser(user)
    }

    fun addWorkflow(w: Workflow){
        workflows.add(w)
        liveWorkflows.value=workflows
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

    //TODO() SAFELY DELETE THIS FUNCTION
    fun loadWorkflow() : ArrayList<Workflow> {
        workflows = GatewayRequests.readWorkflow(user)
        return workflows
    }
    //TODO() REMOVE GATEWAY REQUEST SAFELY
    fun saveWorkflow(workfloName: String, blockList: ArrayList<Block>) {
        var workflow = Workflow(workfloName)
        workflow.setBlocks(blockList)
        workflows.add(workflow)
        //GatewayRequests.saveWorkflow(user, workflow)
        liveWorkflows.value=workflows
    }

    fun getBlock(user: User, w: String) : ArrayList<Block>? {
        for(item in workflows){
            if(item.getName() == w){
                return item.getBlocks(user)
            }
        }
        return null
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