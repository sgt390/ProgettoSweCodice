package com.megalexa.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.megalexa.R
import com.megalexa.ui.activities.GeneralLoggedActivity
import com.megalexa.ui.activities.ViewBlockActivity
import com.megalexa.ui.activities.ViewBlockListActivity
import com.megalexa.util.view.ItemClickListener

class BlockListViewAdapter(val context: Context): RecyclerView.Adapter<BlockListViewAdapter.BlockListViewHolder>() {

    var dataset = ArrayList<String>()
        set(value) {
            field=value
        }

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(holder: BlockListViewHolder, position: Int) {
        holder.tView?.text = dataset[position]

        holder.setItemClickListener(object: ItemClickListener {

            override fun onClick(view: View?, position: Int) {
                return
            }

            override fun onLongClick(view: View?, position: Int) {
                val activity = context as ViewBlockListActivity
                activity.notifyDeleteBlockListInteraction(position)
            }
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlockListViewHolder {
        return BlockListViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.list_item_layout,
                parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    //ViewHolder is the single list's object
    class BlockListViewHolder(v: View): RecyclerView.ViewHolder(v),View.OnLongClickListener {

        val  tView = v.findViewById<TextView>(R.id.textListItem)
        //the line right below is gonna be useful to delete item
        //val  button= v.findViewById<ImageView>(R.id.mv_block)

        private lateinit var itemClickListener: ItemClickListener

        init{
            tView?.setOnLongClickListener(this)
        }

        fun setItemClickListener(itemClickListener: ItemClickListener){
            this.itemClickListener= itemClickListener
        }
        override fun onLongClick(v: View?): Boolean {
            itemClickListener.onLongClick(v, adapterPosition)
            return true
        }

    }
}




