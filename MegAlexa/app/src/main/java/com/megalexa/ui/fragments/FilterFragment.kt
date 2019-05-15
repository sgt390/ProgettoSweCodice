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
* Bianca Ciuche         || 2019-04-04   || Verifying code
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

class FilterFragment: Fragment() {

    private var filter = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.filter_fragment_layout, container, false)
        val button = view.findViewById<Button>(R.id.confirmItemBotton)
        val numberPicker = view.findViewById<NumberPicker>(R.id.numberpicker)

        button.setOnClickListener {
            //start from 0 so there aren't controls to do
            filter = numberPicker.value
        }
        return view
    }

    fun getFiltered() = filter
}