/*
 *
 *  File name: ListFragment.kt
 *  Version: 1.0.0
 *  Date: 2019-03-05
 *  Author: Andrea Deidda
 *  License:
 *  History:
 *  Author        || Date            || Description
 *  Andrea Deidda || 2019-03-05      || File created
 *  Bianca Ciuche || 2019-03-10      || Verifying code
 *
 */

package com.megalexa.ui.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.megalexa.R
import com.megalexa.ui.activities.CreateBlockActivity

class ListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.list_fragment_layout,container,false)

        val button= view.findViewById<Button>(R.id.confirmItemBotton)

        button.setOnClickListener {

            val activity = context as CreateBlockActivity
            activity.onFragmentClick(this)

        }

        return view
    }
}