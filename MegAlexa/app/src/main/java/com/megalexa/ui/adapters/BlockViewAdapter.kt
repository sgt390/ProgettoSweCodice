/*
 *
 *  File name: BlockViewAdapter.kt
 *  Version: 1.0.0
 *  Date: 2019-03-19
 *  Author: Ludovico Brocca
 *  License:
 *  History:
 *  Author            || Date            || Description
 *  Ludovico Brocca   || 2019-03-19      || File created
 *  Gian Marco Bratzu || 2019-03-24      || Verifying code
 *
 */


package com.megalexa.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.megalexa.R
import com.megalexa.ui.activities.CreateWorkflowActivity
import com.megalexa.ui.activities.ViewBlockActivity
import com.megalexa.util.view.ItemClickListener
import java.util.*

class BlockViewAdapter(val context: Context): RecyclerView.Adapter<BlockViewHolder>(){


    var dataset = ArrayList<String>()
        set(value){
            field= value
        }

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(holder: BlockViewHolder, position: Int) {
        holder.tView?.text = dataset[position]

        holder.setItemClickListener(object: ItemClickListener {
            override fun onClick(view: View?, position: Int) {
                if(context is ViewBlockActivity && holder.tView?.text.toString() == "List") {
                    context.startViewListActivity(position)
                }
            }

            override fun onLongClick(view: View?, position: Int) {
                return
            }
        })

        holder.button.setOnTouchListener{ _, event ->

            if (event.actionMasked==MotionEvent.ACTION_DOWN && (context is ViewBlockActivity)) {
                context.touchHelper?.startDrag(holder)
            }
            if (event.actionMasked==MotionEvent.ACTION_DOWN && context is CreateWorkflowActivity) {
                context.touchHelper?.startDrag(holder)
            }
            false
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlockViewHolder {

        return BlockViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.block_item,
                parent,
                false
            )
        )
    }
    /**
     * Function called to swap dragged items
     */
    fun swapItems(fromPosition: Int, toPosition: Int) {
        if (fromPosition < toPosition) {
            for (i in fromPosition until(toPosition - 1)) {
                dataset.set(i, dataset.set(i+1, dataset.get(i)))
            }
        } else {
            for (i in fromPosition until(toPosition + 1)) {
                dataset.set(i, dataset.set(i-1, dataset.get(i)))
            }
        }

        notifyItemMoved(fromPosition, toPosition)
    }

}

class BlockViewHolder(v: View): RecyclerView.ViewHolder(v),View.OnClickListener{

    val  tView = v.findViewById<TextView>(R.id.block_name)
    val  button= v.findViewById<ImageView>(R.id.mv_block)

    private lateinit var itemClickListener:ItemClickListener

    init{
        tView?.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        itemClickListener.onClick(v,adapterPosition)
    }

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener= itemClickListener
    }

}