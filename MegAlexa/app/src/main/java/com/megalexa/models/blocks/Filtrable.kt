package com.megalexa.models.blocks

interface Filtrable {

    var itemsToShow:Int
    var filtered:Boolean
    fun applyFilter(i:Int) {
        itemsToShow = i
        filtered = true
    }
}