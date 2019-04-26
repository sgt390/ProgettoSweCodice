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
    var tweetText : String = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.write_tweet_layout, container, false)
        val button = view.findViewById<Button>(R.id.twitter_confirm_button)
        val editText = view.findViewById<EditText>(R.id.insert_text_tweet)

        button.setOnClickListener {
            tweetText = editText.text.toString()

            if (tweetText == "") {
                Toast.makeText(context, "field empty", Toast.LENGTH_SHORT).show()
            } else {
                val activity = activity as TwitterActivity
                activity.onFragmentClick(this)
            }
        }
        return view
    }

    fun getTextTweet() = tweetText
}