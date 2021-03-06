/*
 *
 *  File name: BlockFiler.kt
 *  Version: 1.0
 *  Date: 2019-02-05
 *  Author: Mirko Franco
 *  License:
 *  History:
 *  Author          || Date            || Description
 *  Mirko Franco    || 2019-02-05      || File created
 *  Ludovico Brocca || 2019-02-23      || Verifying code
 *
 *
 */

package com.megalexa.models.blocks

import com.megalexa.R
import com.megalexa.util.ApplicationContextProvider

/*
    Check if predecessor is Filtrable in Workflow class!
 */
class Filter(private val limit:Short):Block {
    private var filteringResult= false
    private lateinit var attachedBlock: Block

    override fun getInformation() :String {
        val message=  ApplicationContextProvider.context!!.resources!!.getString(R.string.BlockFilter)
        val items=  ApplicationContextProvider.context!!.resources!!.getString(R.string.Items)
        return "$message $limit $items"
    }

    fun limit() = limit

    /**
     * checks if block is filtrable, upper classes will add filters based on the result
     */
    fun attachTo(block: Block) {
        if(block is Filtrable) {
            attachedBlock = block
            notifyResult(true)
        }else {
            notifyResult(false)
        }
    }

    private fun notifyResult(bool:Boolean) {
        filteringResult=bool
    }

    fun getResult() = filteringResult


    fun attachedBlock() = attachedBlock.getInformation()

}