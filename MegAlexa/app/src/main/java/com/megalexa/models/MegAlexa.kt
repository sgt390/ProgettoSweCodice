/*
 *
 *  File name: MegAlexa.kt
 *  Version: 2.0.0
 *  Date: 2019-03-01
 *  Author: Ludovico Brocca
 *  License:
 *  History:
 *  Author           || Date            || Description
 *  Ludovico Brocca  || 2019-03-01      || File created
 *  Mirko Franco     || 2019-03-10      || Verifying code
 *  Ludovico Brocca  || 2019-03-17      || Add model instance
 *  Matteo Depascale || 2019-03-24      || Verifying code
 */

package com.megalexa.models

import com.megalexa.models.blocks.Block
import com.megalexa.models.workflow.Workflow



class MegAlexa private constructor(
    private var user: User,
    private var workflows: ArrayList<Workflow>) {

    fun addWorkflow(w: Workflow) {
        workflows.add(w)
    }

    fun getWorkflowList(): ArrayList<Workflow> {
        return workflows
    }

    fun setUser(user: User) {
        this.user = user
    }

    fun getUser() : User{
        return user
    }

    fun addBlock(workflowName:String,block:Block) {
        for(item in workflows) {
            if(item.getName()== workflowName)
                item.addBlock(block)
        }
    }

    fun addBlock(workflowName: String,block:Block, position: Int) {
        for(item in workflows) {
            if(item.getName()== workflowName)
                item.addBlock(position,block)
        }
    }

    fun addWorkflow(workfloName: String, blockList: ArrayList<Block>) {
        val workflow = Workflow(workfloName)
        workflow.setBlocks(blockList)
        workflows.add(workflow)
    }

    fun getBlock(w: String) : ArrayList<Block>? {
        for(item in workflows){
            if(item.getName() == w){
                return item.getBlocks()
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

    fun getWorkflowNames() :ArrayList<String> {
        val names = ArrayList<String>()
        for(item in workflows)
            names.add(item.getName())

        return names
    }


    fun setInstance(param : MegAlexa) {
        this.user = param.user
        this.workflows= param.workflows

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