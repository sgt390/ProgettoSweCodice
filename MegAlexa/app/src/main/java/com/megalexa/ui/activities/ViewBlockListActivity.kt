/*
 *
 *  File name: ViewBlockAcitivity.kt
 *  Version: 1.0.0
 *  Date: 2019-03-17
 *  Author: Ludovico Brocca
 *  License:
 *  History:
 *  Author            || Date            || Description
 *  Ludovico Brocca   || 2019-03-17      || File created
 *  Gian Marco Bratzu || 2019-03-24      || Verifying code
 *
 */
package com.megalexa.ui.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.ContextThemeWrapper
import android.view.View
import com.megalexa.R
import com.megalexa.ui.adapters.BlockListViewAdapter
import com.megalexa.util.InjectorUtils
import com.megalexa.viewModel.ViewModelBlockList
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
}
