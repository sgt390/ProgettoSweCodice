/*
 *
 *  File name: Block.kt
 *  Version: 1.0
 *  Date: 2019-02-04
 *  Author: Ludovico Brocca
 *  License:
 *  History:
 *  Author           || Date            || Description
 *  Ludovico Brocca  || 2019-02-04      || File created
 *  Matteo Depascale || 2019-02-23       || Verifying code
 *
 */

package com.megalexa.models.blocks

import java.io.Serializable

interface Block : Serializable{

    fun getInformation():String

}