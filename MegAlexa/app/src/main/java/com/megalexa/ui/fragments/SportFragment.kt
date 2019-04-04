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
import android.widget.*
import com.megalexa.R
import com.megalexa.ui.activities.CreateBlockActivity
import com.megalexa.models.connectors.ConnectorSport
import org.jetbrains.anko.support.v4.find
import org.jetbrains.anko.support.v4.findOptional

class SportFragment : Fragment() {
    private var url = ""
    //      TODO IMPORTAN change feed... matteo si sta occupando di procurare i nuovi feed
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.sport_fragment_layout, container, false)

        val tennis = view.findViewById<LinearLayout>(R.id.Tennis_News)
        val calcio = view.findViewById<LinearLayout>(R.id.Calcio_News)

        tennis.setOnClickListener {

            url = "Tennis url"
            val isValid = ConnectorSport(url).valid()
            val activity = activity as CreateBlockActivity
            activity.onFragmentClick(this)
        }


        calcio.setOnClickListener {

            url = "calcio url"
            val isValid = ConnectorSport(url).valid()
            val activity = activity as CreateBlockActivity
            activity.onFragmentClick(this)
        }

        return view
    }
    fun getUrl(): String {
        return url
    }

}
