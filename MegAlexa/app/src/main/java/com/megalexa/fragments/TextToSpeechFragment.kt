package com.megalexa.fragments

import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.megalexa.R

class TextToSpeechFragment: Fragment(){


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.text_to_speech_fragment_layout,container,false)

        val button= view.findViewById<Button>(R.id.confirm_button)
        val editText= view.findViewById<EditText>(R.id.insert_text)

        button.setOnClickListener {

            //ViewModel.addTtsBLock(editText.text)
            Toast.makeText(view.context,"Button CLicked", Toast.LENGTH_SHORT).show()
        }


        return view
    }

}