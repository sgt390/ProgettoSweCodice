/*
* File: TelegramFragment.kt
* Version: 1.0.0
* Date: 2019-03-27
* Author: Andrea Deidda
*
* License:
*
* History:
* Author                || Date         || Description
* Andrea Deidda         || 2019-03-27   || Create class TelegramFragment
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
import com.megalexa.models.connectors.ConnectorTelegram
import com.megalexa.ui.activities.CreateBlockActivity

class TelegramFragment: Fragment() {

    private var telephoneNumber = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.telegram_fragment_layout, container, false)

        val button = view.findViewById<Button>(R.id.confirm_button)
        val editPhone = view.findViewById<EditText>(R.id.editPhone)

        button.setOnClickListener {

            telephoneNumber = editPhone.text.toString()
            val isValid = ConnectorTelegram(telephoneNumber).valid()

            if (telephoneNumber == "" || !isValid || telephoneNumber.length != 10) {
                Toast.makeText(context, "number not valid", Toast.LENGTH_SHORT).show()
            }
            else {
                telephoneNumber = editPhone.text.toString()
                /*val activity = activity as CreateBlockActivity
                activity.onFragmentClick(this)*/
            }

        }

        return view
    }
    fun getTelephoneNumber() = telephoneNumber
}