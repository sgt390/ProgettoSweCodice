package com.megalexa.models.workflow


import com.megalexa.models.blocks.Block
import java.util.*
import kotlin.collections.ArrayList

class Workflow(private val name:String) {
    private  var  blockList: ArrayList<Block> = ArrayList()
    private  var workflowName = name

    fun addBlock(block: Block){
        blockList.add(block)
    }

    fun getCount():Int {
        return blockList.size
    }

    fun removeBlockAt(position:Int) {
        blockList.removeAt(position)
    }

    fun removeBlock(block:Block) {
        blockList.remove(block)
    }

    fun addBlock(position:Int,block:Block) {
        blockList.add(position,block)
    }

    fun getBlocks() : ArrayList<Block>{
        return blockList
    }

    fun setBlocks(blockList : ArrayList<Block>){
        this.blockList = blockList
    }

    override fun toString(): String {
        return workflowName
    }

    fun getName() : String{
        return workflowName
    }

    fun setName(newName : String) {
        this.workflowName = newName
    }

    companion object {
        fun clone(workflow: Workflow): Workflow {
            val result= Workflow(workflow.getName())
            val list= ArrayList<Block>()
            result.setName(workflow.getName())
            Collections.copy(list,workflow.getBlocks())
            result.setBlocks(list)
            return result

        }
    }
}