package com.megalexa.models.workflow

import android.arch.lifecycle.MutableLiveData
import com.megalexa.models.User
import com.megalexa.models.blocks.Block
import com.megalexa.util.GatewayRequests
import org.json.JSONArray
import org.json.JSONObject

class Workflow(private val name:String) {
    private  var  blockList: ArrayList<Block> = ArrayList()
    private var liveBlockList= MutableLiveData<ArrayList<Block>>()
    private  var workflowName = name

    init {
        liveBlockList.value=blockList
    }


    fun addBlock(block: Block){
        blockList.add(block)
        liveBlockList.value=blockList

    }

    fun getCount():Int {
        return blockList.size
    }


    fun removeBlockAt(position:Int) {
        blockList.removeAt(position)
        liveBlockList.value=blockList
    }

    fun removeBlock(block:Block) {
        blockList.remove(block)
        liveBlockList.value=blockList
    }

    fun getBlocks() : ArrayList<Block>{
        return blockList
    }
    //TODO() SAFELY DELETE THIS FUNCTION
    fun getBlocks(user: User) : ArrayList<Block>{
        blockList = GatewayRequests.readBlocks(user, this)!!
        return blockList
    }

    fun setBlocks(blockList : ArrayList<Block>){
        this.blockList = blockList
        liveBlockList.value=blockList
    }

    override fun toString(): String {
        return workflowName
    }

    fun getName() : String{
        return workflowName
    }

   /* fun toSON() : JSONObject{
        var workflowName = JSONObject()
        var workflow = JSONArray()
        for(block in blockList){
            workflow.put(block.toJSON())
        }
        workflowName.put(name, workflow)
        return workflowName
    }
 */
}