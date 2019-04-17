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

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.megalexa.R
import com.megalexa.ui.activities.CreateBlockActivity
import com.megalexa.models.connectors.ConnectorCrypto
import org.jetbrains.anko.support.v4.find
import org.jetbrains.anko.support.v4.findOptional

class CryptoFragment : Fragment() {
    private var url = ""
    private var cardinality=10
    //      TODO IMPORTAN change feed... matteo si sta occupando di procurare i nuovi feed
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.crypto_fragment_layout, container, false)

        val filter= view.findViewById<Button>(R.id.button_filter)
        val first = view.findViewById<TextView>(R.id.COINDESK_id)
        val second = view.findViewById<TextView>(R.id.CRYPTOCONTROL_TOPNEWS_id)
        val third = view.findViewById<TextView>(R.id.CRYPTOCONTROL_LATESTNEWS_id)
        val fourth = view.findViewById<TextView>(R.id.CRYPTOCONTROL_GENERALCRYPTO_id)
        val fifth = view.findViewById<TextView>(R.id.CRYPTOCONTROL_BLOCKCHAIN_id)
        val sixth = view.findViewById<TextView>(R.id.CRYPTOCONTROL_MINING_id)
        val seventh = view.findViewById<TextView>(R.id.CRYPTOCONTROL_BITCOIN_id)
        val eighth = view.findViewById<TextView>(R.id.CRYPTOCONTROL_EUTHEREUM_id)


        filter.setOnClickListener{
            val builder = AlertDialog.Builder(ContextThemeWrapper(context,R.style.Theme_AppCompat_Dialog))
            val pickerLayout =inflater.inflate(R.layout.simple_number_picker,null)
            val picker=pickerLayout.findViewById<NumberPicker>(R.id.number_picker)
            picker.minValue=0
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

        first.setOnClickListener {

            url = "https://www.coindesk.com/feed"
            val activity = activity as CreateBlockActivity
            activity.onFragmentClick(this)
        }


        second.setOnClickListener {

            url = "https://cryptocontrol.io/feed"
            val activity = activity as CreateBlockActivity
            activity.onFragmentClick(this)
        }

        third.setOnClickListener {

            url = "https://cryptocontrol.io/feed?latest=true"
            val activity = activity as CreateBlockActivity
            activity.onFragmentClick(this)
        }
        fourth.setOnClickListener {

            url = "https://cryptocontrol.io/feed/category/general"
            val activity = activity as CreateBlockActivity
            activity.onFragmentClick(this)
        }
        fifth.setOnClickListener {

            url = "https://cryptocontrol.io/feed/category/blockchain"
            val activity = activity as CreateBlockActivity
            activity.onFragmentClick(this)
        }
        sixth.setOnClickListener {

            url = "https://cryptocontrol.io/feed/category/mining"
            val activity = activity as CreateBlockActivity
            activity.onFragmentClick(this)
        }
        seventh.setOnClickListener {

            url = "https://cryptocontrol.io/feed/coin/bitcoin"
            val activity = activity as CreateBlockActivity
            activity.onFragmentClick(this)
        }
        eighth.setOnClickListener {

            url = "https://cryptocontrol.io/feed/coin/ethereum"
            val activity = activity as CreateBlockActivity
            activity.onFragmentClick(this)
        }


        return view
    }
    fun getUrl()=url
    fun getCardinality()=cardinality
}
