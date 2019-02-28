package com.megalexa.models

class User(uID: String ,mail: String,value: String) {

    private val userID= uID
    private val email= mail
    private val name= value

    fun getMail():String{
        return email
    }
    fun getName():String{
        return name
    }
    fun getID():String{
        return userID
    }
}