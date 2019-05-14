/*
 *
 *  File name:
 *  Version:
 *  Date:
 *  Author:
 *  License:
 *  History:
 *  Author        || Date            || Description
 * /
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