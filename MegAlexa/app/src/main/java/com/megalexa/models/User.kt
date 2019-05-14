/*
 *
 *  File name:
 *  Version: 1.0.0
 *  Date: 2019-02-20
 *  Author: Mirko Franco
 *  License:
 *  History:
 *  Author          || Date            || Description
 *  Mirko Franco    || 2019-02-20      || File created
 *  Ludovico Brocca || 2019-02-27      || Verifying code
 */

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