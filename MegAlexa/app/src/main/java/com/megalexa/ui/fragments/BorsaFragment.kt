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

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.megalexa.R
import com.megalexa.ui.activities.CreateBlockActivity
import com.megalexa.models.connectors.ConnectorBorsa
import org.jetbrains.anko.support.v4.find
import org.jetbrains.anko.support.v4.findOptional

class BorsaFragment : Fragment() {
    private var url = ""
    //      TODO IMPORTAN change feed... matteo si sta occupando di procurare i nuovi feed
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.borsa_fragment_layout, container, false)

        val first = view.findViewById<TextView>(R.id.firstBorsaURL)
        val second = view.findViewById<TextView>(R.id.secondBorsaURL)
        val third = view.findViewById<TextView>(R.id.thirdBorsaURL)

        first.setOnClickListener {

            url = "first URL"
            val isValid = ConnectorBorsa(url).valid()
            val activity = activity as CreateBlockActivity
            activity.onFragmentClick(this)
        }


        second.setOnClickListener {

            url = "second URL"
            val isValid = ConnectorBorsa(url).valid()
            val activity = activity as CreateBlockActivity
            activity.onFragmentClick(this)
        }

        third.setOnClickListener {

            url = "third URL"
            val isValid = ConnectorBorsa(url).valid()
            val activity = activity as CreateBlockActivity
            activity.onFragmentClick(this)
        }


        return view
    }
    fun getUrl(): String {
        return url
    }

}
