package com.megalexa.adapters.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.amazonaws.services.dynamodbv2.model.StreamViewType
import com.megalexa.R


class WorkflowViewAdapter(private val dataset: ArrayList<String>, private val context: Context):RecyclerView.Adapter<WorkflowViewHolder>(){


    override fun onBindViewHolder(holder: WorkflowViewHolder, position: Int) {
        holder.tView?.text = dataset[position]
    }

    override fun getItemCount(): Int {
        return dataset.size
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkflowViewHolder {
        return WorkflowViewHolder(LayoutInflater.from(context).inflate(R.layout.item_workflow, parent, false))
    }
}




class WorkflowViewHolder(v: View): RecyclerView.ViewHolder(v) {

     val  tView = v.findViewById<TextView>(R.id.workflow_name)

}