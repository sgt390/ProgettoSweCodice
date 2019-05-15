/*
 *
 *  File name: WorkflowViewModel.kt
 *  Version: 1.0.0
 *  Date: 2019-03-03
 *  Author: Ludovico Brocca
 *  License:
 *  History:
 *  Author            || Date            || Description
 *  Ludovico Brocca   || 2019-03-03      || File created
 *  Gian Marco Bratzu || 2019-03-07      || Verifying code
 *
 */

package com.megalexa.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.megalexa.models.MegAlexa
import com.megalexa.models.blocks.*
import com.megalexa.models.workflow.Workflow
import com.megalexa.util.service.BlockWeatherService
import com.megalexa.util.service.WorkflowService
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.doAsyncResult
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.util.*

class WorkflowViewModel(private val app: MegAlexa, private var workflowName:String) :ViewModel() {

    private var blockNames = MutableLiveData<ArrayList<String>>()
    private var workflow = Workflow(workflowName)
    private var error= MutableLiveData<String>()

    /**
     * returns the adapter for the current block names that must be displayed
     */
    fun getLiveBlockNames(): LiveData<ArrayList<String>> {
        if(blockNames.value == null)
            loadBlocks()
        return blockNames
    }

    fun getLiveError(): LiveData<String> {
        if(error.value==null)
            error.value=""

        return error
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

    private fun refreshBlocks() {
        val blocks =workflow.getBlocks()
        val names= ArrayList<String>()
        for(item in blocks) {
            names.add(item.getInformation())
        }
        blockNames.postValue(names)
    }

    fun postError(str:String) {
        error.postValue(str)
    }

    fun workflowIsValid() : Boolean{
        val list= workflow.getBlocks()
        val iterator= list.iterator()
        var result= true

        iterator.forEach {
            if(it is Filter && (!iterator.hasNext() || !(iterator.next() is Filtrable))) {
                postError("workflow is invalid, check for filters position")
                result = false
            }
        }

        return result
    }

    fun saveWorkflow() {
        try{
            val res=isUnique(workflow.getName())
            if(res) {
                workflow.setName(workflowName)
                app.addWorkflow(workflow)
                doAsync{WorkflowService.postOperation(WorkflowService.convertToJSON(workflow))}
            }
        }catch(e:IOException) {
            postError("Connection Error!")
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
     try {
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

     }catch (e: Exception) {
         postError("Connection Error!")
     }

        refreshBlocks()
    }
    fun addTwoArgBlock(blockType:String,param:String,paramTwo:String) {
        try {
            val block:Block
            when(blockType) {

                "Email" -> {
                    block=BlockReadEmail(param,paramTwo)
                    workflow.addBlock(block)
                }
                "Calendar" -> {
                    block=BlockCalendar(param,paramTwo)
                    workflow.addBlock(block)
                }

            }

        }catch (e: Exception) {
            postError("Connection Error!")
        }

        refreshBlocks()
    }
    fun setName(param : String) {
        this.workflowName = param
    }

    fun updateWorkflow() {

        try{
            if(workflowIsValid()) {
                val list = app.getWorkflowList()
                val iterator= list.iterator()
                while (iterator.hasNext()) {
                    iterator.forEach {
                        if (it.getName() == workflow.getName()) {
                            val oldName = it.getName()
                            iterator.remove()
                            workflow.setName(workflowName)
                            doAsync {
                                WorkflowService.deleteOperation(listOf(Pair("userID",app.getUser().getID()), Pair("workflowName", oldName)))
                                WorkflowService.putOperation(WorkflowService.convertToJSON(workflow))
                            }
                        }
                    }
                    list.add(workflow)
                }
                refreshBlocks()
            }
        }catch(e:IOException) {
            postError("Connection Error!")
        }
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
    fun cancelPreviousIntent() {
        refreshBlocks()
    }

    fun removeBlockAt(position: Int) {
        try{
            val list = workflow.getBlocks()
            list.removeAt(position)
            /*
            doAsync {
                WorkflowService.deleteOperation(listOf(
                Pair("userID",app.getUser().getID()),
                Pair("workflowName", workflowName),
                Pair("oldBlockIndex", position.toString())
            ))}
            */
            refreshBlocks()
        }catch(e:IOException) {
            postError("Connection Error!")
        }
    }

    fun addFilter(cardinality: Short) {
        val list=workflow.getBlocks()
        list.add(Filter(cardinality))
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

        var json=JSONObject()
        try {
            json=parseOpenweather(city).get()
        }catch(e:IOException){
            postError("Connection Error!")
        }

        return json
    }
}

class WorkflowViewModelFactory(private val app: MegAlexa,private val workflowName: String):
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WorkflowViewModel(app,workflowName) as T
    }
}
