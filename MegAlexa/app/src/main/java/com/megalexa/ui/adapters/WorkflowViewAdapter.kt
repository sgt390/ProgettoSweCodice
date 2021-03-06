/*
 *
 *  File name: BlockViewListAdapter.kt
 *  Version: 1.0.0
 *  Date: 2019-03-21
 *  Author: Ludovico Brocca
 *  License:
 *  History:
 *  Author            || Date            || Description
 *  Ludovico Brocca   || 2019-03-21      || File created
 *  Gian Marco Bratzu || 2019-03-24      || Verifying code
 *
 */


package com.megalexa.ui.adapters

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
import com.megalexa.util.view.ItemClickListener


class WorkflowViewAdapter(private val dataset: ArrayList<String>, private val context: Context):RecyclerView.Adapter<WorkflowViewHolder>(){


    override fun onBindViewHolder(holder: WorkflowViewHolder, position: Int) {
        holder.workflowName?.text = dataset[position]

        holder.setItemClickListener(object: ItemClickListener {

            override fun onClick(view: View?, position: Int) {
                val intent = Intent(context,ViewBlockActivity::class.java)
                intent.putExtra("WORKFLOW_NAME",dataset[position])
                val activity= context as GeneralLoggedActivity
                activity.passIntentForResult(intent)
            }


            override fun onLongClick(view: View?, position: Int) {
                val activity = context as GeneralLoggedActivity
                activity.notifyDeleteInteraction(position)
            }
        })

    }

    override fun getItemCount(): Int {
        return dataset.size
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkflowViewHolder {
        return WorkflowViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_workflow,
                parent,
                false
            )
        )
    }
}


class WorkflowViewHolder(v: View): RecyclerView.ViewHolder(v),View.OnClickListener,View.OnLongClickListener {

    val  workflowName :TextView? = v.findViewById(R.id.workflow_name)
    private lateinit var itemClickListener: ItemClickListener

    init{
        workflowName?.setOnClickListener(this)
        workflowName?.setOnLongClickListener(this)
    }

    override fun onClick(v: View?) {
        itemClickListener.onClick(v,adapterPosition)
    }

    fun setItemClickListener(itemClickListener: ItemClickListener){
        this.itemClickListener= itemClickListener
    }

    override fun onLongClick(v: View?): Boolean {
        itemClickListener.onLongClick(v,adapterPosition)
        return true
    }
}