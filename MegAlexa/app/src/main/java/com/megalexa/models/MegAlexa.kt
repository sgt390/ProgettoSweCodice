package com.megalexa.models

import com.megalexa.models.blocks.Block
import com.megalexa.models.workflow.Workflow
import com.megalexa.util.GatewayRequests


class MegAlexa {

    private var workflows= ArrayList<Workflow>()
    private var user : User

    private constructor(user: User, workflows: ArrayList<Workflow>){
        this.user=user
        this.workflows=workflows
    }

    //TODO() SAFELY DELETE THIS FUNCTION
    fun saveUser(user: User) {
        this.user = user
        //GatewayRequests.saveUser(user)
    }

    fun addWorkflow(w: Workflow) {
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

    //TODO() SAFELY DELETE THIS FUNCTION
    fun loadWorkflow() : ArrayList<Workflow> {
        workflows = GatewayRequests.readWorkflow(user)
        return workflows
    }
    //TODO() REMOVE GATEWAY REQUEST SAFELY
    fun saveWorkflow(workfloName: String, blockList: ArrayList<Block>) {
        val workflow = Workflow(workfloName)
        workflow.setBlocks(blockList)
        workflows.add(workflow)
        //GatewayRequests.saveWorkflow(user, workflow)
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


    companion object {

        @Volatile private var instance: MegAlexa?= null
        //Singleton instantiation
        fun getInstance() = instance?: synchronized(this) {
            instance ?: MegAlexa.build().also { instance = it }
        }


        //Builder functions
        var workflows =ArrayList<Workflow>()
        var user= User("dummyUID","dummyEmail","dummyName")

        fun workflows(workflows: ArrayList<Workflow>) = apply { this.workflows= workflows }
        fun user(user: User) = apply { this.user=user }
        fun user(userID:String,email:String,name:String) =apply { this.user= User(userID,email,name) }
        fun build() = MegAlexa(user,workflows)

    }

}








