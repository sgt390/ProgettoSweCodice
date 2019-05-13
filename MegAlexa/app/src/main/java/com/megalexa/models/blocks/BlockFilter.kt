package com.megalexa.models.blocks

/*
    Check if predecessor is Filtrable in Workflow class!
 */
class Filter(private val limit:Short):Block {
    private var filteringResult= false
    private lateinit var attachedBlock: Block

    override fun getInformation() :String {

        return "filters a block for $limit items"
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