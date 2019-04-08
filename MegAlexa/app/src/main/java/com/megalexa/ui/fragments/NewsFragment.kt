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
import com.megalexa.R
import com.megalexa.ui.activities.CreateBlockActivity
import android.widget.TextView
import com.megalexa.models.connectors.ConnectorNews

class NewsFragment : Fragment() {
    private var url = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.news_fragment_layout, container, false)

        val bbc = view.findViewById<TextView>(R.id.BBC_News)
        val sky = view.findViewById<TextView>(R.id.Sky_News)
        val googleNews = view.findViewById<TextView>(R.id.Google_News)
        val cnbc = view.findViewById<TextView>(R.id.Cnbc_News)
        val wallStreet = view.findViewById<TextView>(R.id.Wallstreet_News)
        val ansa = view.findViewById<TextView>(R.id.Ansa_News)

        bbc.setOnClickListener {
            url = "http://feeds.bbci.co.uk/news/video_and_audio/world/rss.xml"
            val activity = activity as CreateBlockActivity
            activity.onFragmentClick(this)
        }
        sky.setOnClickListener {
            url = "http://feeds.skynews.com/feeds/rss/world.xml"
            val activity = activity as CreateBlockActivity
            activity.onFragmentClick(this)
        }
        googleNews.setOnClickListener {
            url = "https://news.google.com/rss?hl=it&gl=IT&ceid=IT:it"
            val activity = activity as CreateBlockActivity
            activity.onFragmentClick(this)
        }
        cnbc.setOnClickListener {
            url = "https://www.cnbc.com/id/100727362/device/rss/rss.html"
            val activity = activity as CreateBlockActivity
            activity.onFragmentClick(this)
        }
        wallStreet.setOnClickListener{
            url = "https://feeds.a.dj.com/rss/RSSWorldNews.xml"
            val activity = activity as CreateBlockActivity
            activity.onFragmentClick(this)
        }
        ansa.setOnClickListener {
            url = "https://www.ansa.it/sito/ansait_rss.xml"
            val activity = activity as CreateBlockActivity
            activity.onFragmentClick(this)
        }
        return view
    }
    fun getUrl(): String {
        return url
    }

}
