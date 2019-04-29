package com.megalexa.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.megalexa.R

class BlockListViewAdapter(val context: Context): RecyclerView.Adapter<BlockListViewAdapter.BlockListViewHolder>() {

    var dataset = ArrayList<String>()
        set(value) {
            field=value
        }

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(holder: BlockListViewHolder, position: Int) {
        holder.tView?.text = dataset[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlockListViewHolder {
        return BlockListViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.block_item,
                parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    //ViewHolder is the single list's object
    class BlockListViewHolder(v: View): RecyclerView.ViewHolder(v) {

        val  tView = v.findViewById<TextView>(R.id.block_name)
        //the line right below is gonna be useful to delete item
        //val  button= v.findViewById<ImageView>(R.id.mv_block)

    }
}




