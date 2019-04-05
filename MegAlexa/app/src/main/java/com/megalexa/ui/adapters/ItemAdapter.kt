package com.megalexa.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.megalexa.R
import kotlinx.android.synthetic.main.item_layout.view.*

class ItemAdapter(private val list: ArrayList<String>): RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    private var mListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onDeleteClick(position: Int)
        fun onClickItem(position: Int)
        fun onClickDone(position: Int)
        fun onClickCancel(position: Int)
    }

    fun setOnClickListener(listener: OnItemClickListener) {
        mListener = listener
    }
    class ItemViewHolder(itemView: View, listener: OnItemClickListener?) : RecyclerView.ViewHolder(itemView){
        var title: TextView = itemView.textView as TextView
        var editItem: EditText = itemView.editItem as EditText
        var buttonDone: Button = itemView.buttonDone as Button
        var buttonCancel: Button = itemView.buttonCancel as Button
        private var imageDelete: ImageView = itemView.delete_image as ImageView

        init {
            imageDelete.setOnClickListener {
                if(listener != null){
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onDeleteClick(position)
                    }
                }
            }
            buttonCancel.setOnClickListener {
                if(listener != null){
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onClickCancel(position)
                    }
                }
            }
            buttonDone.setOnClickListener {
                if(listener != null){
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onClickDone(position)
                    }
                }
            }
            itemView.setOnClickListener {
                if(listener != null){
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onClickItem(position)
                    }
                }
            }
        }
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.title.text = list[position]
        holder.editItem.setText(list[position])
    }

    override fun getItemCount() = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapter.ItemViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return ItemViewHolder(v,mListener)
    }
}