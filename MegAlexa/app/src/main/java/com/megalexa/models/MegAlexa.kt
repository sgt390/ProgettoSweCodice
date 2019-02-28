package com.megalexa.models

import com.megalexa.models.workflow.Workflow


class MegAlexa(val u:User) {

    private var workflows = ArrayList<Workflow>()
    private  val user:User

    init{

        user= User(u.getID(),u.getMail(), u.getName())
    }

    fun addWorkflow(w: Workflow){
        workflows.add(w)
    }

    fun getWorkflowList(): ArrayList<Workflow> {

        return workflows
    }

}