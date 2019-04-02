package com.megalexa.models.blocks

class BlockItem(private val _title: String, private val _body: String) {

    private var title: String = ""
    private var body: String = ""

    init{
        title = _title
        body = _body
    }

    fun getTitle() = title

    fun getBody() = body

    fun setTitle(_title: String){
        title = _title
    }

    fun setBody(_body: String){
        body = _body
    }

}