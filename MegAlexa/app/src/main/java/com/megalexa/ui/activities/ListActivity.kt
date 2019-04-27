/*
* File: ListActivity.kt
* Version: 1.0.0
* Date: 2019-04-27
* Author: Andrea Deidda
*
* License:
*
* History:
* Author                || Date         || Description
* Andrea Deidda         || 2019-02-17   || Create file
*                       ||              ||
*/
//package com.megalexa.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.*
import com.megalexa.R
import com.megalexa.ui.adapters.ItemAdapter
import kotlinx.android.synthetic.main.list_fragment_layout.*
/*
class ListActivity: AppCompatActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_fragment_layout)

        val list: ArrayList<String> = ArrayList()

        recyclerView.apply {

            // use a linear layout manager
            layoutManager = LinearLayoutManager(context)

            // specify an viewAdapter (see also next example)
            adapter = ItemAdapter(list)

        }

        val buttonAdd = findViewById<Button>(R.id.addListItemButton)
        val buttonConfirm = findViewById<Button>(R.id.confirmItemBotton)

        (recyclerView.adapter as ItemAdapter).setOnClickListener(object : ItemAdapter.OnItemClickListener {
            override fun onDeleteClick(position: Int) {
                list.remove(list[position])
                (recyclerView.adapter as ItemAdapter).notifyItemRemoved(position)
            }

            override fun onClickItem(position: Int) {
                val editItem = findViewById<EditText>(R.id.editItem)
                val textView = findViewById<TextView>(R.id.textView)
                val imageDelete = findViewById<ImageView>(R.id.delete_image)
                val buttonDone = findViewById<Button>(R.id.buttonDone)
                val buttonCancel = findViewById<Button>(R.id.buttonCancel)
                editItem.setText(list[position])
                editItem.visibility = View.VISIBLE
                textView.visibility = View.INVISIBLE
                imageDelete.visibility = View.INVISIBLE
                buttonDone.visibility = View.VISIBLE
                buttonCancel.visibility = View.VISIBLE
            }

            override fun onClickDone(position: Int) {
                val editItem = findViewById<EditText>(R.id.editItem)
                val textView = findViewById<TextView>(R.id.textView)
                val imageDelete = findViewById<ImageView>(R.id.delete_image)
                val buttonDone = findViewById<Button>(R.id.buttonDone)
                val buttonCancel = findViewById<Button>(R.id.buttonCancel)
                list[position] = editItem.text.toString()
                editItem.visibility = View.INVISIBLE
                textView.visibility = View.VISIBLE
                imageDelete.visibility = View.VISIBLE
                buttonDone.visibility = View.GONE
                buttonCancel.visibility = View.GONE
                (recyclerView.adapter as ItemAdapter).notifyItemChanged(position)
            }

            override fun onClickCancel(position: Int) {
                val editItem = findViewById<EditText>(R.id.editItem)
                val textView = findViewById<TextView>(R.id.textView)
                val imageDelete = findViewById<ImageView>(R.id.delete_image)
                val buttonDone = findViewById<Button>(R.id.buttonDone)
                val buttonCancel = findViewById<Button>(R.id.buttonCancel)
                editItem.visibility = View.INVISIBLE
                textView.visibility = View.VISIBLE
                imageDelete.visibility = View.VISIBLE
                buttonDone.visibility = View.GONE
                buttonCancel.visibility = View.GONE
            }

        })

        buttonAdd.setOnClickListener {
            val editTitle = findViewById<EditText>(R.id.insertItemText)
            if(editTitle.text.toString() == "")
                Toast.makeText(this, "Field empty!", Toast.LENGTH_SHORT).show()
            else{
                list.add(editTitle.text.toString())
                editTitle.setText("")
            }
            (recyclerView.adapter as ItemAdapter).notifyItemInserted(list.size)
        }

        buttonConfirm.setOnClickListener{
            if(list.isEmpty())
                Toast.makeText(this, "Missing items!", Toast.LENGTH_SHORT).show()
        }
    }
}*/