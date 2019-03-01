package com.megalexa.viewModel

import android.arch.lifecycle.AndroidViewModel
import com.megalexa.models.MegAlexa
import com.megalexa.models.User
import com.megalexa.models.workflow.Workflow
import com.megalexa.util.GatewayRequests

class ViewModelMain{

    private var app: MegAlexa = MegAlexa()



    fun saveUser(userID : String, name: String, email: String){
        app.saveUser(User(userID, name, email))
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