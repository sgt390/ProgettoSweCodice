package com.megalexa.ui.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.megalexa.R
import com.megalexa.ui.activities.CreateBlockActivity
import com.megalexa.util.view.FragmentClickListener

class ListFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.list_fragment_layout,container,false)

        val button= view.findViewById<Button>(R.id.confirmItemBotton)

        button.setOnClickListener {

            val activity = context as CreateBlockActivity
            activity.onFragmentClick(this)

        }

        return view
    }

    override fun onClick(v: View?) {
        TODO("is it here? idk") //To change body of created functions use File | Settings | File Templates.
    }
}