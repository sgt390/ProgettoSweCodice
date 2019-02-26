package com.megalexa.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.megalexa.R

class AlarmClockFragment: Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        //must be changed later
        val view = inflater.inflate(R.layout.rss_fragment_layout,container,false)

        /*val button= view.findViewById<Button>(R.id.confirm_button)


        button.setOnClickListener {

            //add selected uri

            Toast.makeText(view.context,"Button CLicked",Toast.LENGTH_SHORT).show()
        }*/


        return view
    }

}