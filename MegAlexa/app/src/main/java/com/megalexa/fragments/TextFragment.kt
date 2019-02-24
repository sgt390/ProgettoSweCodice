/*
 * File: TextFragment.kt
 * Version: 0.0.1
 * Date: 2019-02-23
 * Author: Andrea Deidda
 * License:
 *
 * History:
 * Author           || Date         || Description
 * Andrea Deidda    || 2019-02-23   || creating file and header
 *
 */
package com.megalexa.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.megalexa.R

class TextFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.layout_textblock,container,false)

        /*val button= view.findViewById<Button>(R.id.confirm_button)

        button.setOnClickListener {

            //add selected uri

            Toast.makeText(view.context,"Button Clicked",Toast.LENGTH_SHORT).show()
        }*/
        return view
    }
}