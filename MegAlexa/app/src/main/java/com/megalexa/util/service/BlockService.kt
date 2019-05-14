/*
 *
 *  File name:  BlockService.kt
 *  Version: 1.0.0
 *  Date: 2019-03-03
 *  Author: Ludovico Brocca
 *  License:
 *  History:
 *  Author           || Date            || Description
 *  Ludovico Brocca  || 2019-03-03      || File created
 *  Mirko Franco     || 2019-03-05      || Verifying code
 *
 */

package com.megalexa.util.service


abstract class BlockService : Service() {

    override val resource: String
        get() = "block"


}