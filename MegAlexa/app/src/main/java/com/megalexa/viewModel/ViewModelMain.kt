package com.megalexa.viewModel


import android.util.Log
import com.megalexa.models.MegAlexa
import com.megalexa.models.User
import com.megalexa.models.workflow.Workflow


class ViewModelMain{

    private var app: MegAlexa = MegAlexa()



    fun saveUser(userID : String, name: String, email: String){
        app.saveUser(com.megalexa.models.User(userID, name, email))
    }

    fun setUser(user: com.amazon.identity.auth.device.api.authorization.User){
        app.setUser(com.megalexa.models.User(user.userId, user.userName, user.userName))
    }

    fun fetchWorkflow() : ArrayList<Workflow>{
        return app.loadWorkflow()
    }

    override fun toString(): String {
        return app.getUser().toJSON().toString()
    }
    /**
     * returns a list of workflow names from db
     * and adds it to the app
     */
    /*fun fetchWorkflows(u: User ): ArrayList<String>{

        //fetch from db
        //call functions on app
        //return

        return ArrayList()
    }

    /** returns a list of strings that represent the block inisde the given workflow
     *
     */
    fun fetchBlocks(u:User,w: Workflow): ArrayList<String> {

        //fetch from db
        //call functions on app
        //return

        return ArrayList()
    }

     //fetch from db
        //call functions on app
        //return
*/
}