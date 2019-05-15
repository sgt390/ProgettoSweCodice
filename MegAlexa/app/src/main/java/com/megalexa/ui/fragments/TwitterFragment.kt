/*
* File: TwitterFragment.kt
* Version: 1.0.0
* Date: 2019-03-27
* Author: Andrea Deidda
*
* License:
*
* History:
* Author                || Date         || Description
* Andrea Deidda         || 2019-03-27   || Create class TwitterFragment
* Mirko Franco          || 2019-03-30   || Verifying code
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
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.Toast
import com.megalexa.R
import com.megalexa.ui.activities.TwitterActivity

class TwitterFragment: Fragment() {

    var screenName : String = ""
    private var cardinality=10

    //val consumer_api_key = ApplicationContextProvider.context!!.getResources()!!.getString(R.string.consumer_api_key_twitter) //"INSERT CONSUMER API KEY IN RES/VALUE/STRINGS"
    //val consumer_api_secret_key = ApplicationContextProvider.context!!.resources!!.getString(R.string.consumer_api_key_secret_twitter)//"INSERT CONSUMER API KEYS SECRET IN RES/VALUE/STRINGS"
    // val access_token_twit =ApplicationContextProvider.context!!.resources!!.getString(R.string.access_token_twitter)//"INSERT ACCESS TOKEN"
    //val access_token_twit_secret =ApplicationContextProvider.context!!.resources!!.getString(R.string.access_token_secret_twitter) // "INSERT ACCESS TOKEN SECRET"

    val consumer_api_key =""
    val consumer_api_secret_key=""
    val access_token_twit =""
    val access_token_twit_secret = ""

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.twitter_fragment_layout, container, false)
        val filter= view.findViewById<Button>(R.id.button_filter)
        val button = view.findViewById<Button>(R.id.twitter_confirm_button)
        val editText = view.findViewById<EditText>(R.id.twitter_screenName)

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

        button.setOnClickListener {

            screenName = editText.text.toString()

            if (screenName == "" || screenName[0] != '#') {
                Toast.makeText(context, "Invalid hashtag", Toast.LENGTH_SHORT).show()
            } else {
                    val activity = activity as TwitterActivity
                    activity.onFragmentClick(this)
                }
            }
            return view
        }

    fun getTwit() = screenName
    fun getConsumer_api() = consumer_api_key
    fun getConsumer_api_secret() = consumer_api_secret_key
    fun getAccess_token_() = access_token_twit
    fun getAccess_token_twit_() = access_token_twit_secret
    fun getCardinality()=cardinality
}