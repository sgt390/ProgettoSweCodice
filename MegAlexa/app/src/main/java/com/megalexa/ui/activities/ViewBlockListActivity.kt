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
package com.megalexa.ui.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.Log
import android.view.ContextThemeWrapper
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.megalexa.R
import com.megalexa.ui.adapters.BlockListViewAdapter
import com.megalexa.ui.adapters.BlockViewAdapter
import com.megalexa.util.InjectorUtils
import com.megalexa.util.view.ItemMoveCallback
import com.megalexa.viewModel.ViewModelBlockList
import com.megalexa.viewModel.WorkflowViewModel
import kotlinx.android.synthetic.main.activity_view_block.*
import kotlinx.android.synthetic.main.list_activity_layout.*
import kotlin.concurrent.thread

class ViewBlockListActivity: AppCompatActivity(), View.OnClickListener, View.OnLongClickListener  {

    companion object {
        private lateinit var viewModel: ViewModelBlockList
    }

    private lateinit var rec_view: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_activity_layout)
        var title: String = ""
        var blockPosition: Int = 0

        if(savedInstanceState == null){

            val extras :Bundle? = intent.extras
            if(extras==null){
                title="EXTRAS NULLI"
            } else {
                title= extras.getString("WORKFLOW_NAME")!!
                blockPosition = extras.getInt("blockPosition")
            }
        }else{
            title= savedInstanceState.getSerializable("WORKFLOW_NAME") as String
            blockPosition = savedInstanceState.getSerializable("blockPosition") as Int
        }

        rec_view=findViewById(R.id.itemListView)
        rec_view.layoutManager= LinearLayoutManager(this)
        //workflow_title.text= title
        val factory= InjectorUtils.provideBlockListViewModel(title, blockPosition)

        viewModel = ViewModelProviders.of(this,factory).get(ViewModelBlockList::class.java)
        viewModel.setFromExistingWorkflow(title)

        val observer = Observer<ArrayList<String>>{
            val adapter = BlockListViewAdapter(this@ViewBlockListActivity)
            adapter.dataset=it!!
            runOnUiThread{
                rec_view.adapter= adapter
            }
        }
        viewModel.getLiveBlockList().observe(this,observer)

        button_cancel_list.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v) {
            button_cancel_list -> {
                thread(start = true) {
                    finish()
                }
            }
        }
    }

    override fun onLongClick(v: View?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    fun notifyDeleteBlockListInteraction(position: Int) {


        val builder= android.support.v7.app.AlertDialog.Builder(ContextThemeWrapper(this@ViewBlockListActivity,R.style.AlertDialogCustom))
        val confirmDeletion={
                _: DialogInterface, _: Int -> viewModel.removeListItemAt(position)
        }
        val cancelDeletion= {
                _: DialogInterface, _:Int ->
        }

        with(builder) {

            setTitle("Delete Item")
            setPositiveButton("Confirm", confirmDeletion)
            setNegativeButton("Cancel", cancelDeletion)
        }

        builder.show()
    }
    /*
    val buttonAdd = findViewById<Button>(R.id.addListItemButton)
    val buttonConfirm = findViewById<Button>(R.id.confirmItemBotton)

    (recyclerView.adapter as ListItemAdapter).setOnClickListener(object : ListItemAdapter.OnItemClickListener {
        override fun onDeleteClick(position: Int) {
            list.remove(list[position])
            (recyclerView.adapter as ListItemAdapter).notifyItemRemoved(position)
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
            (recyclerView.adapter as ListItemAdapter).notifyItemChanged(position)
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
        (recyclerView.adapter as ListItemAdapter).notifyItemInserted(list.size)
    }

    buttonConfirm.setOnClickListener{
        if(list.isEmpty())
            Toast.makeText(this, "Missing items!", Toast.LENGTH_SHORT).show()
    }
    */
}
