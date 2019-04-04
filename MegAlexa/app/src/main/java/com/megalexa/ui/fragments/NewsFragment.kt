/*
* File: RssFragment.kt
* Version: 1.0.0
* Date: 2019-02-18
* Author: Ludovico Brocca
*
* License:
*
* History:
* Author                || Date         || Description
* Ludovico Brocca       || 2019-02-18   || Writing class RssFragment
*                       ||              ||
*/

package com.megalexa.ui.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.megalexa.R
import com.megalexa.ui.activities.CreateBlockActivity
import com.megalexa.models.connectors.ConnectorNews

class NewsFragment : Fragment() {
    private var url = ""
//      TODO IMPORTAN change feed... matteo si sta occupando di procurare i nuovi feed
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.news_fragment_layout, container, false)

        val bbc = view.findViewById<TextView>(R.id.BBC_News)
        val sky = view.findViewById<TextView>(R.id.Sky_News)
        val google_news = view.findViewById<TextView>(R.id.Google_News)

        bbc.setOnClickListener {

            url = "http://feeds.bbci.co.uk/news/video_and_audio/world/rss.xml"
            val isValid = ConnectorNews(url).valid()
            val activity = activity as CreateBlockActivity
            activity.onFragmentClick(this)
        }


        sky.setOnClickListener {

            url = "http://feeds.bbci.co.uk/news/video_and_audio/world/rss.xml"
            val isValid = ConnectorNews(url).valid()
            val activity = activity as CreateBlockActivity
            activity.onFragmentClick(this)
        }

        google_news.setOnClickListener {

            url = "https://news.google.com/rss?hl=it&gl=IT&ceid=IT:it"
            val isValid = ConnectorNews(url).valid()
            val activity = activity as CreateBlockActivity
            activity.onFragmentClick(this)
        }


        return view
    }
    fun getUrl(): String {
        return url
    }

}
