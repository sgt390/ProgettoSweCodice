package com.megalexa.util

class InvalidBlockException(private val error:String): Exception() {

    fun getErrorMessage() = error

}