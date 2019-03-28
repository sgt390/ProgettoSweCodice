/*
* File: NewsFragment.kt
* Version: 1.0.0
* Date: 2019-03-27
* Author: Andrea Deidda
*
* License:
*
* History:
* Author                || Date         || Description
* Andrea Deidda         || 2019-03-27   || Create class NewsFragment
*                       ||              ||
*/

package com.megalexa.ui.fragments
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import com.megalexa.R
import com.megalexa.ui.activities.CreateBlockActivity

class NewsFragment: Fragment() {

    private var newsSite = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.news_fragment_layout, container, false)
        val button = view.findViewById<Button>(R.id.confirm_button)

        val spinnerNews = view.findViewById<Spinner>(R.id.planets_spinner)
        button.setOnClickListener {

            newsSite = spinnerNews.toString()
            //la prima voce " - " dello spinner corrisponde ad una voce vuota
            if (newsSite == "-") {
                Toast.makeText(context, "Nothing selected", Toast.LENGTH_SHORT).show()
            } else {
                newsSite = spinnerNews.toString()
                /*val activity = activity as CreateBlockActivity
                activity.onFragmentClick(this)*/
            }
        }
        return view
    }

    fun getNewsSite() = newsSite
}