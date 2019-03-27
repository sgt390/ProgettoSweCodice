/*
* File: TwitterFragment.kt
* Version: 1.0.0
* Date: 2019-03-27
* Author: Andrea Deidda
*
* License:
*
* History:
* Author                || Date         || Description
* Andrea Deidda         || 2019-03-27   || Create class TwitterFragment
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
import com.megalexa.models.connectors.ConnectorReadTwitter
import com.megalexa.ui.activities.CreateBlockActivity

class TwitterFragment: Fragment() {

    private var mail = ""
    private var password = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.twitter_fragment_layout, container, false)

        val button = view.findViewById<Button>(R.id.confirm_button)
        val editTextEmail = view.findViewById<EditText>(R.id.editMail)
        val editTextPassword = view.findViewById<EditText>(R.id.editPsw)

        button.setOnClickListener {

            mail = editTextEmail.text.toString()
            password = editTextPassword.text.toString()

            if (mail == "" || password == "") {
                Toast.makeText(context, "missing mail or password", Toast.LENGTH_SHORT).show()
            } else {
                mail = editTextEmail.text.toString()
                password = editTextPassword.text.toString()
                /*val activity = activity as CreateBlockActivity
                activity.onFragmentClick(this)*/
            }

        }

        return view
    }
}