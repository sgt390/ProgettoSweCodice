/*
* File: AmazonMusicFragment.kt
* Version: 1.0.0
* Date: 2019-03-28
* Author: Andrea Deidda
*
* License:
*
* History:
* Author                || Date         || Description
* Andrea Deidda         || 2019-03-28   || Writing class AmazonMusicFragment
*                       ||              ||
*/
package com.megalexa.ui.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.megalexa.R

class AmazonMusicFragment: Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.amazonmusic_fragment_layout,container,false)
        return view
    }
}