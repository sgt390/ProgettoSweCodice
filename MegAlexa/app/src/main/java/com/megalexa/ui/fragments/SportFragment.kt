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
import android.widget.*
import com.megalexa.R
import com.megalexa.ui.activities.CreateBlockActivity
import com.megalexa.models.connectors.ConnectorSport
import org.jetbrains.anko.support.v4.find
import org.jetbrains.anko.support.v4.findOptional

class SportFragment : Fragment() {
    private var url = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.sport_fragment_layout, container, false)

        val tennis = view.findViewById<LinearLayout>(R.id.Tennis_News)
        val calcio = view.findViewById<LinearLayout>(R.id.Calcio_News)
        val basket = view.findViewById<LinearLayout>(R.id.Basket_News)
        val nfl = view.findViewById<LinearLayout>(R.id.nfl_News)
        val formula1 = view.findViewById<LinearLayout>(R.id.f1_News)
        val motorcycle = view.findViewById<LinearLayout>(R.id.Motorcycle_News)

        tennis.setOnClickListener {
            url = "https://www.atptour.com/en/media/rss-feed/xml-feed"
            val isValid = ConnectorSport(url).valid()
            val activity = activity as CreateBlockActivity
            activity.onFragmentClick(this)
        }
        calcio.setOnClickListener {
            url = "https://www.goal.com/feeds/en/news"
            val isValid = ConnectorSport(url).valid()
            val activity = activity as CreateBlockActivity
            activity.onFragmentClick(this)
        }
        basket.setOnClickListener {
            url = "http://www.espn.com/espn/rss/nba/news"
            val isValid = ConnectorSport(url).valid()
            val activity = activity as CreateBlockActivity
            activity.onFragmentClick(this)
        }
        nfl.setOnClickListener {
            url = "http://www.espn.com/espn/rss/nfl/news"
            val isValid = ConnectorSport(url).valid()
            val activity = activity as CreateBlockActivity
            activity.onFragmentClick(this)
        }
        formula1.setOnClickListener {
            url = "http://www.autosport.com/rss/feed/f1"
            val isValid = ConnectorSport(url).valid()
            val activity = activity as CreateBlockActivity
            activity.onFragmentClick(this)
        }
        motorcycle.setOnClickListener {
            url = "http://www.espn.com/espn/rss/rpm/news"
            val isValid = ConnectorSport(url).valid()
            val activity = activity as CreateBlockActivity
            activity.onFragmentClick(this)
        }
        return view
    }
    fun getUrl(): String {
        return url
    }

}
