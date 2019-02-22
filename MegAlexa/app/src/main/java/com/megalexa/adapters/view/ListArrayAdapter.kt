package com.megalexa.adapters.view


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.megalexa.R
import android.graphics.drawable.Drawable

import android.support.v4.content.res.ResourcesCompat
import android.widget.*
import com.megalexa.models.blocks.Block


class ListArrayAdapter(context: Context,layout: Int,dataset: ArrayList<Pair<String,Drawable>>):ArrayAdapter<Pair<String,Drawable>>() {
    init {
        super(context,R.layout.recycler_view_item,dataset)
    }



    override fun getView(position:Int, convertView: View?, parent: ViewGroup?):View?{
        val viewHolder:ViewHolderItem
        var convertView = convertView


        if(convertView==null){
            // If convert view is null then inflate a custom view and use it as convert view
            convertView = LayoutInflater.from(parent?.context).
                inflate(R.layout.recycler_view_item,parent,false)

            // Create a new view holder instance using convert view
            viewHolder = ViewHolderItem(convertView)

            // Set the view holder as convert view tag
            convertView.tag = viewHolder
        }else{
            /*
                If convert view is not null then
                initialize the view holder using convert view tag.
            */
            viewHolder = convertView.tag as ViewHolderItem
        }


        val list = getBlockList(parent!!.context)

        viewHolder.textViewItem.text = list[position].first
        viewHolder.imageViewItem.setImageDrawable(list[position].second)

        return convertView

    }

    override fun getItem(position: Int): Any {


    }

    override fun getItemId(position: Int): Long {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


     fun getBlockList(context:Context):List<Pair<String,Drawable?>> {

         val list= getTitlesList()

        return listOf(Pair(list[0], ResourcesCompat.getDrawable(context.resources, R.drawable.ic_mr_button_connected_10_light, null)
         ))

    }


    fun getTitlesList():List<String> {

        return listOf("Add FeedRSS")

    }







    class ViewHolderItem(view:View) {

        var textViewItem :TextView= view.findViewById(R.id.block_label)
        var imageViewItem: ImageView = view.findViewById(R.id.block_logo)
        val layout:LinearLayout = view.findViewById(R.id.itemLayout)


    }

}


