/*
 *
 *  File name: TwiiterReadTimeLineUser.kt
 *  Version: 1.0.0
 *  Date: 2019-03-27
 *  Author: Andrea Deidda
 *  License:
 *  History:
 *  Author        || Date            || Description
 *  Andrea Deidda || 2019-03-27      || File created
 *  Mirko Franco  || 2019-03-30      || Verifying code
 */

package com.megalexa.ui.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.NumberPicker
import com.megalexa.R
import com.megalexa.ui.activities.TwitterActivity

class TwitterReadTimeLineUser: Fragment() {

    private var cardinality=10

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.twitter_read_user_timeline, container, false)
        val filter= view.findViewById<Button>(R.id.button_filter)
        val button = view.findViewById<Button>(R.id.twitter_confirm_button)

        filter.setOnClickListener{
            val builder = AlertDialog.Builder(ContextThemeWrapper(context, R.style.Theme_AppCompat_Dialog))
            val pickerLayout =inflater.inflate(R.layout.simple_number_picker,null)
            val picker=pickerLayout.findViewById<NumberPicker>(R.id.number_picker)
            picker.minValue=1
            picker.maxValue=10

            val confirmFilter = {
                    _: DialogInterface, _:Int -> cardinality = picker.value
            }

            val cancelFilter = {
                    _: DialogInterface, _:Int ->
            }

            with(builder) {
                setView(pickerLayout)
                setPositiveButton("Confirm",confirmFilter)
                setNegativeButton("Cancel",cancelFilter)
            }

            builder.show()
        }

        button.setOnClickListener {
            val activity = activity as TwitterActivity
            activity.onFragmentClick(this)
        }
        return view
    }

    fun getCardinality()=cardinality
}