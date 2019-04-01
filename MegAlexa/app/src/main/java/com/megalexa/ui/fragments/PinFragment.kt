/*
* File: PinFragment.kt
* Version: 1.0.0
* Date: 2019-03-27
* Author: Gian Marco Bratzu
*
* License:
*
* History:
* Author                || Date         || Description
* Ludovico Brocca       || 2019-03-27   || Writing class PinFragment
*                       ||              ||
*/

package com.megalexa.ui.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.megalexa.R
import com.megalexa.ui.activities.CreateBlockActivity

class PinFragment : Fragment(){
    private var pin = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.pin_fragment_layout,container,false)
        val button = view.findViewById<Button>(R.id.SaveButtonPin)
        val editText = view.findViewById<EditText>(R.id.pinID)

        button.setOnClickListener {
            pin = editText.text.toString()
            if(pin == "") {
                Toast.makeText(context, "error whith your pin", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(context,pin, Toast.LENGTH_SHORT).show()
                val activity= activity as CreateBlockActivity
                activity.onFragmentClick(this)
            }

        }

        return view
    }

}

