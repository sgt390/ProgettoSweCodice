package com.megalexa.ui.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.megalexa.R

class ListFragment2: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.edit_item_layout, container, false)

        val buttonSave = view.findViewById<Button>(R.id.save_button)
        val cancelButton = view.findViewById<Button>(R.id.cancel_button)

        val fragment: Fragment = ListFragment()
        val transaction = fragmentManager?.beginTransaction()

        buttonSave.setOnClickListener{
            /*val newTitle = view.findViewById<EditText>(R.id.editTitle)
            val newBody = view.findViewById<EditText>(R.id.editBody)*/

            transaction?.replace(R.id.fragment_container, fragment)?.addToBackStack("")?.commit()
        }

        cancelButton.setOnClickListener {

            transaction?.replace(R.id.fragment_container, fragment)?.addToBackStack("")?.commit()
        }

        return view
    }
}