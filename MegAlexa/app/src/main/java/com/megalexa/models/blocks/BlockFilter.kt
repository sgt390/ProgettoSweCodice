package com.megalexa.models.blocks

/*
    Check if predecessor is Filtrable in Workflow class!
 */
internal class Filter(private val cardinality:Short):Block {
    override fun getInformation():String  {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun cardinality() = cardinality
}