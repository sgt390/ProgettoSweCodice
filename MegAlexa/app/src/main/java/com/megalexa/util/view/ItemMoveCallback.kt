package com.megalexa.util.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import com.megalexa.ui.activities.CreateWorkflowActivity
import com.megalexa.ui.activities.ViewBlockActivity
import com.megalexa.ui.adapters.BlockViewAdapter

class ItemMoveCallback(private val context: Context, dragDirs: Int, swipeDirs: Int) : ItemTouchHelper.SimpleCallback(dragDirs, swipeDirs)
{


    override fun onSwiped(p0: RecyclerView.ViewHolder, p1: Int) {
        return
    }

    override fun onMove(p0: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        if (context is CreateWorkflowActivity)
            context.swapItems(viewHolder.adapterPosition, target.adapterPosition)
        if (context is ViewBlockActivity)
            context.swapItems(viewHolder.adapterPosition, target.adapterPosition)

        return true
    }

}
