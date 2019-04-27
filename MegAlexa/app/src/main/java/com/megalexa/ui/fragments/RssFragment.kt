/*
* File: RssFragment.kt
* Version: 1.0.0
* Date: 2019-02-18
* Author: Ludovico Brocca
*
* License:
*
* History:
* Author                || Date         || Description
* Ludovico Brocca       || 2019-02-18   || Writing class RssFragment
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
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.Toast
import com.megalexa.R
import com.megalexa.ui.activities.CreateBlockActivity
import com.megalexa.models.connectors.ConnectorFeedRss

class RssFragment : Fragment() {
    private var url=""
    private var cardinality=10

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.rss_fragment_layout,container,false)

        val filter= view.findViewById<Button>(R.id.button_filter)
        val button= view.findViewById<Button>(R.id.confirmItemBotton)
        val editText= view.findViewById<EditText>(R.id.insert_URL)


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

        button.setOnClickListener {

            url=editText.text.toString()
            val isValid=ConnectorFeedRss(url).valid()

            if(url == ""|| !isValid) {
                Toast.makeText(context, "url is invalid", Toast.LENGTH_SHORT).show()
            }
            else{
                url= editText.text.toString()
                val activity= activity as CreateBlockActivity
                activity.onFragmentClick(this)
            }

        }

    return view
    }


    fun getUrl()=url
    fun getCardinality()=cardinality
}

