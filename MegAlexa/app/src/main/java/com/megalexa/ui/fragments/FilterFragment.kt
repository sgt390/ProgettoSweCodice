/*
* File: FilterFragment.kt
* Version: 1.0.0
* Date: 2019-03-28
* Author: Andrea Deidda
*
* License:
*
* History:
* Author                || Date         || Description
* Andrea Deidda         || 2019-03-28   || Create class FilterFragment
*                       ||              ||
*/

package com.megalexa.ui.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.NumberPicker
import com.megalexa.R
import com.megalexa.ui.activities.CreateBlockActivity
import org.jetbrains.anko.sdk25.coroutines.onClick

class FilterFragment: Fragment() {
    private var filter = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.filter_fragment_layout, container, false)
        val button = view.findViewById<Button>(R.id.confirm_button)
        val numberPicker = view.findViewById<NumberPicker>(R.id.numberpicker)
        button.setOnClickListener {
            //start from 0 so there aren't controls to do
            filter = numberPicker.value

            /*val activity= activity as CreateBlockActivity
            activity.onFragmentClick(this)*/
        }
        return view
    }

    fun getFiltered() = filter
}