/*
 *
 *  File name: FragmentClickListener.kt
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

import android.support.v4.app.Fragment

interface FragmentClickListener {
    fun onFragmentClick(sender: Fragment)
}