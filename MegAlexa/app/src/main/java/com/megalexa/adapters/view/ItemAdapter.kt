package com.megalexa.adapters.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.megalexa.R
import com.megalexa.models.blocks.BlockItem

class ItemAdapter(private val list: ArrayList<BlockItem>): RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    private var mListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onDeleteClick(position: Int)
        //fun onClickItem(position: Int)
    }

    fun setOnClickListener(listener: OnItemClickListener) {
        mListener = listener
    }
    class ItemViewHolder(itemView: View, listener: OnItemClickListener?) : RecyclerView.ViewHolder(itemView){
        var title:TextView = itemView.findViewById(R.id.textView)
        var body:TextView = itemView.findViewById(R.id.textView2)
        var imageDelete:ImageView = itemView.findViewById(R.id.delete_image)

        init {
            imageDelete.setOnClickListener {
                if(listener != null){
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onDeleteClick(position)
                    }
                }
            }

            /*itemView.setOnClickListener {
                if(listener != null){
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onClickItem(position)
                    }
                }
            }*/
        }
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.title.text = list[position].getTitle()
        holder.body.text = list[position].getBody()
    }

    override fun getItemCount() = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapter.ItemViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return ItemViewHolder(v,mListener)
    }
}