/*
 *
 *  File name: ItemClickListener.kt
 *  Version: 1.0.0
 *  Date: 2019-02-25
 *  Author: Ludovico Brocca
 *  License:
 *  History:
 *  Author           || Date            || Description
 *  Ludovico Brocca  || 2019-02-25      || File created
 *  Matteo Depascale || 2019-03-01      || Verifying code
 *
 */

package com.megalexa.util.view

import android.view.View


interface ItemClickListener {
    fun onClick(view: View?, position: Int)
    fun onLongClick(view: View?,position: Int)

}