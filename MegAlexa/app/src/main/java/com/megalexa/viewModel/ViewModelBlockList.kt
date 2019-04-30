package com.megalexa.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.util.Log
import com.megalexa.models.MegAlexa
import com.megalexa.models.blocks.BlockList
import com.megalexa.models.workflow.Workflow
import com.megalexa.models.workflow.Workflow.Companion.clone
import com.megalexa.util.service.BlockListService.convertFromJSON
import org.json.JSONArray
import org.json.JSONObject
import java.util.ArrayList

class ViewModelBlockList(private var workflowName: String, private var blockPosition: Int) : ViewModel() {
    private var blockList = MutableLiveData<ArrayList<String>>()
    private var workflow = clone(Workflow(workflowName))

    init {
        //populate this.workflow
        setFromExistingWorkflow(workflowName)
    }

    /**
     * returns the adapter for the current list items that must be displayed
     */
    fun getLiveBlockList(): LiveData<ArrayList<String>> {
        if(blockList.value == null)
            loadListItems()
        return blockList
    }

    /**
     * gets current information regarding a specific workflow and adds it to LiveData Object
     */
    private fun loadListItems() {
        val myHandler = android.os.Handler()
        myHandler.postDelayed({
            val arrayJSON : JSONArray = (workflow.getBlocks().get(blockPosition) as BlockList).getList()
            var listJSON = ArrayList<String>()

            //need to convert JSONArray to ArrayList<String>

            for(i in 0..arrayJSON.length()-1) {
                val itemListJSON=arrayJSON.getString(i)
                listJSON.add(itemListJSON)
            }
            blockList.value=listJSON
        }, 0)
    }

    fun setFromExistingWorkflow(wName: String) {
        val list = MegAlexa.getInstance().getWorkflowList()
        for (item in list) {
            if(item.getName()== wName) {
                this.workflow= Workflow.clone(item)
                this.workflowName=wName
            }

        }
    }

    private fun refreshBlockList() {
        val arrayJSON : JSONArray = (workflow.getBlocks()[blockPosition] as BlockList).getList()
        var listJSON = ArrayList<String>()
        for(i in 0..arrayJSON.length()) {
            listJSON.add(arrayJSON[i] as String)
        }
        blockList.postValue(listJSON)
    }
}

class BlockListViewModelFactory(private var workflowName: String, private var blockPosition: Int):
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ViewModelBlockList(workflowName, blockPosition) as T
    }
}