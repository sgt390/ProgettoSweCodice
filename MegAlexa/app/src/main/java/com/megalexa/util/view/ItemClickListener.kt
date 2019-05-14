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

package com.megalexa.util.view

import android.view.View


interface ItemClickListener {
    fun onClick(view: View?, position: Int)
    fun onLongClick(view: View?,position: Int)

}