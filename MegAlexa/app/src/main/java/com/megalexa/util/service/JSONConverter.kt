/*
 *
 *  File name: JSONConverter.kt
 *  Version: 1.0.0
 *  Date: 2019-03-01
 *  Author: Ludovico Brocca
 *  License:
 *  History:
 *  Author          || Date            || Description
 *  Ludovico Brocca || 2019-03-01      || File created
 *  Andrea Deidda   || 2019-03-07      || Verifying code
 *
 */

package com.megalexa.util.service

import org.json.JSONObject

interface JSONConverter {

    fun <T> convertToJSON(t:T): JSONObject

    open fun convertFromJSON(jsonObject: JSONObject):Any

}