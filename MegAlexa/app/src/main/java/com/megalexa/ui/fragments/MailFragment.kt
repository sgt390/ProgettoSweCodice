/*
 *
 *  File name: CryptoFragment.kt
 *  Version: 1.0.0
 *  Date: 2019-03-05
 *  Author: Andrea Deidda
 *  License:
 *  History:
 *  Author        || Date            || Description
 *  Andrea Deidda || 2019-04-20      || File created
 *  Bianca Ciuche || 2019-04-29     || Verifying code
 *
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
import com.megalexa.ui.activities.GoogleActivity

class MailFragment: Fragment(){

    private var cardinality = 5

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.mail_fragment_layout,container,false)
        val filter = view.findViewById<Button>(R.id.button_filter)
        val button= view.findViewById<Button>(R.id.confirmItemBotton_mail)

        filter.setOnClickListener{
            val builder = AlertDialog.Builder(ContextThemeWrapper(context,R.style.Theme_AppCompat_Dialog))
            val pickerLayout =inflater.inflate(R.layout.simple_number_picker,null)
            val picker=pickerLayout.findViewById<NumberPicker>(R.id.number_picker)
            picker.minValue=1
            picker.maxValue=5

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

                val activity= activity as GoogleActivity
                activity.onFragmentClick(this)
        }

        return view
    }


    fun getCardinality() = cardinality

}