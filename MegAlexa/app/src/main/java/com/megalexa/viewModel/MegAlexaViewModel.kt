package com.megalexa.viewModel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.megalexa.models.MegAlexa
import com.megalexa.models.blocks.Block
import org.json.JSONObject

class MegAlexaViewModel(private val app: MegAlexa): ViewModel() {


    /**this functions sets the correct instance for the MegAlexa object
     * fetches from SharedInstances and decides if it' necessary to call api gateway
     * to read the correct instance
     */
    fun loadAppContext(userID: String) {
      //todo()
    }

    /**
     * saves information in SharedPreferences when the app closes
     */
    fun saveAppContext(){
       //todo()
    }

    fun setUser(user: com.amazon.identity.auth.device.api.authorization.User) {
        app.setUser(com.megalexa.models.User(user.userId, user.userName, user.userEmail))
    }

    fun saveWorkflow(workfloName: String, blockList: ArrayList<Block>) {
        //todo()
    }

    fun addBlock(workflowName:String,blockType:String,jsonObject: JSONObject) {
        //todo() convert from json and add to block list
    }

    fun addBlock(workflowName: String,blockType: String,jsonObject: JSONObject,position: Int) {
        //todo() convert from json and add to block list
    }

    fun getBlocks(name: String) : ArrayList<String> {
        val blocks = app.getBlock(name)
        val blocksType : ArrayList<String> = ArrayList<String>()
        for(item in blocks!!) {
            blocksType.add(item.getInformation())
        }
        return blocksType
    }

    fun isUserPresent(paramID:String):Boolean {
        if(app.getUser().getID()==paramID)
            return true
        return false
    }

    //more functions to be added
}

class MegAlexaViewModelFactory(private val app: MegAlexa):
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MegAlexaViewModel(app) as T
    }
}
