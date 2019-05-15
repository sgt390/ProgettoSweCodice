/*
 *
 *  File name: InvalidBlockException.kt
 *  Version: 1.0.0
 *  Date: 2019-03-01
 *  Author: Ludovico Brocca
 *  License:
 *  History:
 *  Author            || Date            || Description
 *  Ludovico Brocca   || 2019-03-01      || File created
 *  Gian Marco Bratzu || 2019-03-07      || Verifying code
 *
 */

package com.megalexa.util

class InvalidBlockException(private val error:String): Exception() {

    fun getErrorMessage() = error

}