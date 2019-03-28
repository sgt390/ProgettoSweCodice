/*
* File: YoutubeFragment.kt
* Version: 1.0.0
* Date: 2019-03-28
* Author: Andrea Deidda
*
* License:
*
* History:
* Author                || Date         || Description
* Andrea Deidda         || 2019-03-28   || Writing class YoutubeFragment
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
import com.megalexa.models.connectors.ConnectorYoutube
import com.megalexa.ui.activities.CreateBlockActivity

class YoutubeFragment: Fragment() {

    private var url = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.youtube_fragment_layout, container, false)
        val editText = view.findViewById<EditText>(R.id.insert_URL)
        val button = view.findViewById<Button>(R.id.confirm_button)

        button.setOnClickListener {
            url = editText.text.toString()
            val isValid = ConnectorYoutube(url).valid()

            if(url == "" || !isValid){
                Toast.makeText(context, "url not valid", Toast.LENGTH_SHORT).show()
            } else{
                url = editText.text.toString()
                /*val activity= activity as CreateBlockActivity
                activity.onFragmentClick(this)*/
            }
        }

        return view
    }

    fun getUrl() = url

}