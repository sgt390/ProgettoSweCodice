package com.megalexa.util.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import com.megalexa.ui.adapters.BlockViewAdapter
import com.megalexa.ui.adapters.BlockViewHolder

class ItemMoveCallback(adapter: BlockViewAdapter, context: Context, dragDirs: Int, swipeDirs: Int) : ItemTouchHelper.SimpleCallback(dragDirs, swipeDirs)
{
    var adapter = adapter


    override fun onSwiped(p0: RecyclerView.ViewHolder, p1: Int) {
        return
    }

    override fun onMove(p0: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        adapter.swapItems(viewHolder.adapterPosition, target.adapterPosition)
        return true
    }
}
