package com.megalexa.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.megalexa.models.MegAlexa
import com.megalexa.models.blocks.Block
import com.megalexa.models.blocks.BlockTextToSpeech
import com.megalexa.models.workflow.Workflow
import com.megalexa.util.service.WorkflowService
import org.json.JSONObject

class WorkflowViewModel(private val app: MegAlexa, private var workflowName:String) :ViewModel() {

    private var blockNames = MutableLiveData<ArrayList<String>>()
    private var workflow = Workflow(workflowName)

    /**
     * returns the adapter for the current block names that must be displayed
     */
    fun getLiveBlockNames(): LiveData<ArrayList<String>> {
        if(blockNames.value == null)
            loadBlocks()
        return blockNames
    }

    /**
     * gets current information regarding a specific workflow and adds it to LiveData Object
     */
    private fun loadBlocks() {
        val myHandler = android.os.Handler()
        myHandler.postDelayed({
            val bNames= workflow.getBlocks()
            val names= ArrayList<String>()
            for(item in bNames) {
                names.add(item.getInformation())
            }
            blockNames.value= names
        }, 5000)
    }

    fun refreshBlocks() {
        val blocks =workflow.getBlocks()
        val names= ArrayList<String>()
        for(item in blocks) {
            names.add(item.getInformation())
        }
        blockNames.postValue(names)
    }

    fun saveWorkflow() {
        if(isUnique(workflow.getName())) {
            app.addWorkflow(workflow)
            val json = WorkflowService.convertToJSON(workflow)
            WorkflowService.postOperation(json)
        }
    }

    fun isUnique(wName : String): Boolean {

      val list= app.getWorkflowNames()

        for(item in list) {
            if (wName == item)
                return false
        }
        return true
    }
    fun addBlock(blockType:String,jsonObject: JSONObject) {
        //todo() convert from json and add to block list
    }

    fun addBlock(blockType: String, jsonObject: JSONObject, position: Int) {
        //todo() convert from json and add to block list
    }

    fun addDebug(param:String) {
        workflow.addBlock(BlockTextToSpeech(""))
        refreshBlocks()
    }

    fun setName(param : String) {
        this.workflowName= param
    }
}

class WorkflowViewModelFactory(private val app: MegAlexa,private val workflowName: String):
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WorkflowViewModel(app,workflowName) as T
    }
}