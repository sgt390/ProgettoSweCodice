/*
* File: AlarmClockFragment.kt
* Version: 0.0.1
* Date: 2019-02-23
* Author: Andrea Deidda
* License:
*
* History:
* Author           || Date         || Description
* Andrea Deidda    || 2019-02-23   || creating file and header
* Andrea Deidda    || 2019-02-25   || Insert methods
*/
package com.megalexa.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.megalexa.R


class AlarmClockFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.layout_alarmclock,container,false)

        val button= view.findViewById<Button>(R.id.setButton)
        button.setOnClickListener {
            Toast.makeText(view.context,"Button Clicked",Toast.LENGTH_SHORT).show()
        }
        return view
    }

}