package com.megalexa.ui.activities

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.ContextThemeWrapper
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.amazon.identity.auth.device.AuthError
import com.amazon.identity.auth.device.api.Listener
import com.amazon.identity.auth.device.api.authorization.User
import com.megalexa.R
import com.megalexa.ui.adapters.BlockViewAdapter
import com.megalexa.util.InjectorUtils
import com.megalexa.viewModel.WorkflowViewModel
import kotlinx.android.synthetic.main.activity_view_block.*
import android.support.v7.widget.helper.ItemTouchHelper;
import com.megalexa.util.view.ItemMoveCallback

class ViewBlockActivity:AppCompatActivity(), View.OnClickListener {
    companion object {
        private lateinit var viewModel : WorkflowViewModel
    }
    private lateinit var rec_view: RecyclerView
    var touchHelper:ItemTouchHelper?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_block)
        val title: String?

        if(savedInstanceState == null){

            val extras :Bundle? = intent.extras
            if(extras==null){
                title="EXTRAS NULLI"
            } else {
                title= extras.getString("WORKFLOW_NAME")
            }
        }else{
            title= savedInstanceState.getSerializable("WORKFLOW_NAME") as String
        }
        rec_view=findViewById(R.id.recyclerView_addedBlocksView)
        rec_view.layoutManager= LinearLayoutManager(this)
        workflow_title.text= title
        val factory= InjectorUtils.provideWorkflowViewModelFactory(title!!)

        viewModel = ViewModelProviders.of(this,factory).get(WorkflowViewModel::class.java)
        viewModel.setFromExistingWorkflow(title)
        val observer = Observer<ArrayList<String>>{
            val adapter = BlockViewAdapter(it!!, this@ViewBlockActivity)
            val callback= ItemMoveCallback(adapter,this,ItemTouchHelper.UP.or(ItemTouchHelper.DOWN),0)
            touchHelper= ItemTouchHelper(callback)
            touchHelper?.attachToRecyclerView(rec_view)
            runOnUiThread{
                rec_view.adapter= adapter
            }
        }
        viewModel.getLiveBlockNames().observe(this,observer)
        button_confirm_modification.setOnClickListener(this)
        button_add_blockView.setOnClickListener(this)
        button_cancel_modify.setOnClickListener(this)
        User.fetch(this, object: Listener<User, AuthError> {
            override fun onSuccess(p0: User) {
                viewModel.refreshBlocks()
            }
            override fun onError(p0: AuthError?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

    }

    override fun onClick(v: View?) {
        when(v) {
            button_confirm_modification -> {
                val name= findViewById<TextView>(R.id.workflow_title)
                viewModel.setName(name.text.toString())
                viewModel.updateWorkflow()
                setResult(Activity.RESULT_OK)
                finish()
            }
            button_add_blockView -> startActivityForResult(Intent(this, CreateBlockActivity::class.java),1)
            button_cancel_modify -> {
                setResult(Activity.RESULT_CANCELED)
                finish()
            }
        }
    }


    fun showPopup(v: View?) {

        val builder= AlertDialog.Builder(ContextThemeWrapper(this@ViewBlockActivity,R.style.AlertDialogCustom))
        val inflater= layoutInflater

        val alertDialog= inflater.inflate(R.layout.simple_edit_text,null)
        val edit=alertDialog.findViewById<EditText>(R.id.dialog_edit)

        with(builder){
            setTitle("Change Workflow Name")
            setView(alertDialog)
            setPositiveButton("OK",object: DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    val title=findViewById<TextView>(R.id.workflow_title)
                    title.text=edit.text.toString()
                    dialog!!.dismiss()
                }
            })
            setNegativeButton("Cancel", object :DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    dialog!!.dismiss()
                }

            })

        }
        builder.show()
    }


    fun notifyDeleteBlockInteraction(position: Int) {


        val builder= android.support.v7.app.AlertDialog.Builder(ContextThemeWrapper(this@ViewBlockActivity,R.style.AlertDialogCustom))
        val confirmDeletion={
                _: DialogInterface, _: Int -> viewModel.removeBlockAt(position)
        }
        val cancelDeletion= {
                _:DialogInterface,_:Int ->
        }

        with(builder) {

            setTitle("Delete Block")
            setPositiveButton("Confirm", confirmDeletion)
            setNegativeButton("Cancel", cancelDeletion)
        }

        builder.show()
    }
}