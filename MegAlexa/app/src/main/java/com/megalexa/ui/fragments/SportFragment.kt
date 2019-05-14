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
* Bianca Ciuche         || 2019-01-24   || Verifying code
*/

package com.megalexa.ui.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.NumberPicker
import com.megalexa.R
import com.megalexa.ui.activities.CreateBlockActivity

class SportFragment : Fragment() {
    private var url = ""
    private var cardinality=10

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.sport_fragment_layout, container, false)

        val filter= view.findViewById<Button>(R.id.button_filter)
        val tennis = view.findViewById<LinearLayout>(R.id.Tennis_News)
        val calcio = view.findViewById<LinearLayout>(R.id.Calcio_News)
        val basket = view.findViewById<LinearLayout>(R.id.Basket_News)
        val nfl = view.findViewById<LinearLayout>(R.id.nfl_News)
        val formula = view.findViewById<LinearLayout>(R.id.f1_News)
        val motorcycle = view.findViewById<LinearLayout>(R.id.Motorcycle_News)


        filter.setOnClickListener{
            val builder = AlertDialog.Builder(ContextThemeWrapper(context,R.style.Theme_AppCompat_Dialog))
            val pickerLayout =inflater.inflate(R.layout.simple_number_picker,null)
            val picker=pickerLayout.findViewById<NumberPicker>(R.id.number_picker)
            picker.minValue=1
            picker.maxValue=10

            val confirmFilter = {
                    _: DialogInterface, _:Int -> cardinality = picker.value
            }

            val cancelFilter = {
                    _: DialogInterface, _:Int ->
            }

            with(builder) {
                setView(pickerLayout)
                setPositiveButton("Confirm",confirmFilter)
                setNegativeButton("Cancel",cancelFilter)
            }


            builder.show()

        }
        tennis.setOnClickListener {
            url = "http://www.espn.com/espn/rss/tennis/news"
            val activity = activity as CreateBlockActivity
            activity.onFragmentClick(this)
        }
        calcio.setOnClickListener {
            url = "https://www.goal.com/feeds/en/news"
            val activity = activity as CreateBlockActivity
            activity.onFragmentClick(this)
        }
        basket.setOnClickListener {
            url = "http://www.espn.com/espn/rss/nba/news"
            val activity = activity as CreateBlockActivity
            activity.onFragmentClick(this)
        }
        nfl.setOnClickListener {
            url = "http://www.espn.com/espn/rss/nfl/news"
            val activity = activity as CreateBlockActivity
            activity.onFragmentClick(this)
        }
        formula.setOnClickListener {
            url = "http://www.espn.com/espn/rss/f1/news" // non va
            val activity = activity as CreateBlockActivity
            activity.onFragmentClick(this)
        }
        motorcycle.setOnClickListener {
            url = "http://www.espn.com/espn/rss/rpm/news"
            val activity = activity as CreateBlockActivity
            activity.onFragmentClick(this)
        }
        return view
    }
    fun getUrl()=url
    fun getCardinality()=cardinality

}
