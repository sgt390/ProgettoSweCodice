package com.megalexa.models.workflow

import com.megalexa.models.blocks.Block

class Workflow(private val name:String) {
    private  var  blockList: ArrayList<Block> = ArrayList()
    private  var workflowName=name


    fun addBlock(block: Block){
        blockList.add(block)
    }

    fun getCount():Int {
        return blockList.size
    }

    fun getName() :String {
        return workflowName
    }

    fun removeBlockAt(position:Int) {
        blockList.removeAt(position)
    }

    fun removeBlock(block:Block) {
        blockList.remove(block)
    }

    
}