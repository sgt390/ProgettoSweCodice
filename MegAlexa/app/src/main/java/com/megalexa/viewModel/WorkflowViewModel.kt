package com.megalexa.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.util.Log
import com.google.gson.JsonParser
import com.megalexa.models.MegAlexa
import com.megalexa.models.blocks.*
import com.megalexa.models.workflow.Workflow
import com.megalexa.util.service.BlockWeatherService
import com.megalexa.util.service.WorkflowService
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.doAsyncResult
import org.json.JSONArray
import org.json.JSONObject
import java.util.concurrent.*

import java.util.*

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
        }, 0)
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
            workflow.setName(workflowName)
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
            "TwitterHashtag" -> {
                block= BlockTwitterHashtag(param)
                workflow.addBlock(block)
            }
            "TwitterUserTL" -> {
                block= BlockTwitter(param)
                workflow.addBlock(block)
            }
            "TwitterHomeTL" -> {
                block= BlockTwitterHomeTL()
                workflow.addBlock(block)
            }
            "TwitterWrite" -> {
                block=BlockTwitterWrite()
                workflow.addBlock(block)
            }
            "Weather" -> {
                val json :JSONObject = getWeatherInfo(param)
                block=BlockWeather(json)
                //set all informations
                workflow.addBlock(block)
            }
            "List" -> {
                val json : JSONArray = JSONArray()
                block=BlockList(json)
                workflow.addBlock(block)
            }

        }
        refreshBlocks()
    }

    fun setName(param : String) {
        this.workflowName = param
    }

    fun updateWorkflow() {

        val list = app.getWorkflowList()
        val iterator= list.iterator()
        while (iterator.hasNext()) {
             iterator.forEach {
                 if (it.getName() == workflow.getName()) {
                     iterator.remove()
                     //WorkflowService.deleteOperation(WorkflowService.convertToJSON(iterator))
                 }
             }
            workflow.setName(workflowName)
            list.add(workflow)
        }
        refreshBlocks()
    }

    fun setFromExistingWorkflow(wName: String) {
            val list = app.getWorkflowList()
            for (item in list) {
                if(item.getName()== wName) {
                    this.workflow= Workflow.clone(item)
                    this.workflowName=wName
                }

            }
    }

    fun removeBlockAt(position: Int) {
        val list = workflow.getBlocks()
        list.removeAt(position)
        refreshBlocks()
    }

    fun addFilter(cardinality: Short) {
        val list=workflow.getBlocks()
        list.add(Filter(cardinality))
    }

    fun validateSwap(position: Int) :SwapConfiguration {

        var swapConfiguration=SwapConfiguration.IS_BLOCK

        if((workflow.getBlocks()[position] is Filter)) {
            Log.d("filter","dhfgkhdfkghdkfhgkdhfg")
            swapConfiguration= SwapConfiguration.IS_FILTER
        }else if(position != 0 && workflow.getBlocks()[position-1] is Filter) {
            Log.d("filter attached","dhfgkhdfkghdkfhgkdhfg")
            swapConfiguration=SwapConfiguration.HAS_FILTER_ATTACHED
        }

        return swapConfiguration
    }

    fun swapItems(fromPosition:Int,toPosition:Int){

        val list = workflow.getBlocks()
        Collections.swap(list,fromPosition,toPosition)
        refreshBlocks()
    }

    private fun getWeatherInfo(city:String): JSONObject {

        fun parseOpenweather(city:String) =doAsyncResult{
            return@doAsyncResult BlockWeatherService.getOperation(city)
        }

        return parseOpenweather(city).get()
    }
}

class WorkflowViewModelFactory(private val app: MegAlexa,private val workflowName: String):
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WorkflowViewModel(app,workflowName) as T
    }
}
