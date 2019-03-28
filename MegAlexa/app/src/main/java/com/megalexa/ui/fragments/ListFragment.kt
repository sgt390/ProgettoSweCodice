/*
* File: ListFragment.kt
* Version: 1.0.0
* Date: 2019-03-28
* Author: Andrea Deidda
*
* License:
*
* History:
* Author                || Date         || Description
* Andrea Deidda         || 2019-03-28   || Writing class ListFragment
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

class ListFragment: Fragment() {

    private val List = ArrayList<String>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.list_fragment_layout,container,false)
        val button = view.findViewById<Button>(R.id.confirm_button)

        val editText = view.findViewById<EditText>(R.id.editText)

        button.setOnClickListener {
            /*val activity= activity as CreateBlockActivity
            activity.onFragmentClick(this)*/

        }
        return view
    }
}