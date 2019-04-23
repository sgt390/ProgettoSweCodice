package com.megalexa.ui.adapters

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.megalexa.R
import com.megalexa.ui.activities.ViewBlockActivity
import com.megalexa.util.view.ItemClickListener
import com.megalexa.util.view.ItemMoveCallback
import java.util.*

class BlockViewAdapter(val dataset: ArrayList<String>,val context: Context): RecyclerView.Adapter<BlockViewHolder>(){


    override fun onBindViewHolder(holder: BlockViewHolder, position: Int) {
        holder.tView?.text = dataset[position]

        holder.setItemClickListener(object: ItemClickListener {
            override fun onClick(view: View?, position: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onLongClick(view: View?, position: Int) {
                val activity = context as ViewBlockActivity
                activity.notifyDeleteBlockInteraction(position)
            }
        })
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlockViewHolder {
        return BlockViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_workflow,
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
            for (i in fromPosition..toPosition - 1) {
                dataset.set(i, dataset.set(i+1, dataset.get(i)))
            }
        } else {
            for (i in fromPosition..toPosition + 1) {
                dataset.set(i, dataset.set(i-1, dataset.get(i)))
            }
        }

        notifyItemMoved(fromPosition, toPosition)
    }

}




class BlockViewHolder(v: View): RecyclerView.ViewHolder(v),View.OnClickListener{

    val  tView = v.findViewById<TextView>(R.id.workflow_name)
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