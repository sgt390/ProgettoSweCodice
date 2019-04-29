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
import com.megalexa.ui.activities.TwitterActivity

class WriteTweetFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.write_tweet_layout, container, false)
        val button = view.findViewById<Button>(R.id.twitter_confirm_button)

        button.setOnClickListener {
                val activity = activity as TwitterActivity
                activity.onFragmentClick(this)
        }
        return view
    }

}