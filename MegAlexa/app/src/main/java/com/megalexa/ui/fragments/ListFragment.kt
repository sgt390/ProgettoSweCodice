/*
* File: ListFragment.kt
* Version: 1.0.0
* Date: 2019-03-28
* Author: Andrea Deidda
*
* License:
*
* History:
* Author                || Date         || Description
* Andrea Deidda         || 2019-03-28   || Writing class ListFragment
*                       ||              ||
*/
package com.megalexa.ui.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.megalexa.R
import com.megalexa.models.blocks.BlockItem
import com.megalexa.adapters.view.ItemAdapter

class ListFragment: Fragment() {

    private lateinit var buttonAdd: Button
    private lateinit var buttonConfirm: Button
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.list_fragment_layout, container, false)

        val list: ArrayList<BlockItem> = ArrayList()
        val recyclerView = view.findViewById(R.id.recyclerView) as RecyclerView
        list.add(BlockItem("", ""))

        recyclerView.layoutManager = LinearLayoutManager(container?.context)
        recyclerView.adapter = ItemAdapter(list)

        /*
        * recyclerView.apply {

            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = LinearLayoutManager(container?.context)

            // specify an viewAdapter (see also next example)
            adapter = ItemAdapter(list)

        }*/

        buttonAdd = view.findViewById(R.id.add_button)
        buttonConfirm = view.findViewById(R.id.confirm_button)

        buttonAdd.setOnClickListener {
            list.add(BlockItem("", ""))
            (recyclerView.adapter as ItemAdapter).notifyItemInserted(list.size)
        }

        buttonConfirm.setOnClickListener {

        }
        (recyclerView.adapter as ItemAdapter).setOnClickListener(object : ItemAdapter.OnItemClickListener {
            override fun onDeleteClick(position: Int) {
                list.remove(list[position])
                (recyclerView.adapter as ItemAdapter).notifyItemRemoved(position)

            }

            /*override fun onClickItem(position: Int) {
                val fragment: Fragment = ListFragment2()
                val transaction = fragmentManager?.beginTransaction()
                transaction?.replace(R.id.fragment_container, fragment)?.addToBackStack("")?.commit()
            }*/
        })
        return view
    }

}