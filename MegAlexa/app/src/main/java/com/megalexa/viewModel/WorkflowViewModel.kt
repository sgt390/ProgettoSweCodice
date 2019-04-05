package com.megalexa.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.util.Log
import com.megalexa.models.MegAlexa
import com.megalexa.models.blocks.*
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
        val res=isUnique(workflow.getName())
        if(res) {
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
    fun addOneArgBlock(blockType:String,param:String) {
        val block:Block
        when(blockType) {

            "FeedRss"-> {
                block = BlockFeedRss(param)
                workflow.addBlock(block)
            }
            "News" -> {
                block= BlockNews(param)
                workflow.addBlock(block)
            }
            "Sport" -> {
                block = BlockSport(param)
                workflow.addBlock(block)
            }
            "Pin" -> {
                block = BlockPin(param.toInt())
                workflow.addBlock(block)
            }
            "Text to speech" -> {
                block = BlockTextToSpeech(param)
                workflow.addBlock(block)
            }
            "Crypto" -> {
                block=BlockCrypto(param)
                workflow.addBlock(block)
            }
            "Borsa" -> {
                block=BlockBorsa(param)
                workflow.addBlock(block)
            }

        }
        refreshBlocks()
    }

    fun addBlock(blockType: String, jsonObject: JSONObject, position: Int) {
        //todo() convert from json and add to block list
    }

    fun setName(param : String) {
        this.workflowName = param
        workflow.setName(param)
    }

    fun updateWorkflow() {
        val list = app.getWorkflowList()
        for(item in list) {
           if(item.getName()== workflowName) {
               val index= list.indexOf(item)
               list.remove(item)
               list.add(index,this.workflow)
               val json = WorkflowService.convertToJSON(workflow)
               WorkflowService.putOperation(json)
           }
        }

    }

    fun setFromExistingWorkflow(wName: String) {
            val list = app.getWorkflowList()
            for (item in list) {
                if(item.getName()== wName) {
                    this.workflow= item
                    this.workflowName=wName
                }

            }
    }


}

class WorkflowViewModelFactory(private val app: MegAlexa,private val workflowName: String):
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WorkflowViewModel(app,workflowName) as T
    }
}