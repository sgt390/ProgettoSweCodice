/*
* File: CalendarFragment.kt
* Version: 1.0.0
* Date: 2019-04-30
* Author: Andrea Deidda
*
* License:
*
* History:
* Author                || Date         || Description
* Andrea Deidda         || 2019-04-30   || Create class CalendarFragment
*                       ||              ||
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
import android.widget.Toast
import com.megalexa.R
import com.megalexa.ui.activities.CreateBlockActivity

class CalendarFragment: Fragment() {
    private var cardinality = 0
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.block_calendar_layout, container, false)
        val confirm = view.findViewById<Button>(R.id.confirm)
        val filter = view.findViewById<Button>(R.id.button_filter)
        val mail = view.findViewById<Button>(R.id.mail)
        val password = view.findViewById<Button>(R.id.password)

        filter.setOnClickListener{
            val builder = AlertDialog.Builder(ContextThemeWrapper(context,R.style.Theme_AppCompat_Dialog))
            val pickerLayout =inflater.inflate(R.layout.simple_number_picker,null)
            val picker=pickerLayout.findViewById<NumberPicker>(R.id.number_picker)
            picker.minValue=0
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
        confirm.setOnClickListener {
            if(mail.equals("") && password.equals(""))
                Toast.makeText(context, "missing username or password", Toast.LENGTH_SHORT).show()
            else if(mail.equals("") || password.equals(""))
                Toast.makeText(context, "Fields empties", Toast.LENGTH_SHORT).show()
            else{
                //autenticazione
                val activity = activity as CreateBlockActivity
                activity.onFragmentClick(this)
            }
        }
        return view
    }
    fun getCardinality() = cardinality
}