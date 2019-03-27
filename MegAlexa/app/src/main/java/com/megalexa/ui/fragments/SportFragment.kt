
package com.megalexa.ui.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import com.megalexa.R
import com.megalexa.ui.activities.CreateBlockActivity
import com.megalexa.models.connectors.ConnectorSport

class SportFragment : Fragment(){
    private var url=""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.sport_layout,container,false)

        view.findViewById<ImageButton>(R.id.calcioimg).setOnClickListener {

            url=view.findViewById<EditText>(R.id.calcioUrl).text.toString()
            val isValid=ConnectorSport(url).valid()
            val activity= activity as CreateBlockActivity
            activity.onFragmentClick(this)
        }
        return view
    }


}

