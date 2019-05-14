/*
 *
 *  File name: Connector.kt
 *  Version: 1.0.0
 *  Date: 2019-02-17
 *  Author: Ludovico Brocca
 *  License:
 *  History:
 *  Author          || Date            || Description
 *  Ludovico Brocca || 2019-02-17      || File created
 *  Mirko Franco    || 2019-02-23   || Verifying code
 *
 *
 */

package com.megalexa.models.connectors

interface Connector {
    /**
     * Tries a connection using the URI with minimal parameters
     */
    /*
     variable uri may need a more specific type;
     return type may be less specific.
     */
    fun connect(url:String):String
    /**
     * A connector is valid if connect(..) generates a valid result
     */
    fun valid():Boolean
}