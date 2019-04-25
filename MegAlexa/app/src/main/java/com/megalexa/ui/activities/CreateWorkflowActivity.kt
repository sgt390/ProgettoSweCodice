package com.megalexa.ui.activities

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.Log
import android.view.ContextThemeWrapper
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.amazon.identity.auth.device.AuthError
import com.amazon.identity.auth.device.api.Listener
import com.amazon.identity.auth.device.api.authorization.User
import com.megalexa.R
import com.megalexa.ui.adapters.BlockViewAdapter
import com.megalexa.util.InjectorUtils
import com.megalexa.util.view.ItemMoveCallback
import com.megalexa.viewModel.WorkflowViewModel
import kotlinx.android.synthetic.main.activity_create_workflow.*
import kotlin.concurrent.thread

class CreateWorkflowActivity: AppCompatActivity(), View.OnClickListener {

    var touchHelper:ItemTouchHelper?= null
    private lateinit var rec_view: RecyclerView
    companion object {
        private lateinit var viewModel : WorkflowViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_workflow)
        val factory= InjectorUtils.provideWorkflowViewModelFactory("")
        viewModel = ViewModelProviders.of(this,factory).get(WorkflowViewModel::class.java)
        rec_view= findViewById(R.id.recyclerView_addedBlocksOnCreation)
        rec_view.layoutManager= LinearLayoutManager(this)
        val observer = Observer<ArrayList<String>>{
            val adapter = BlockViewAdapter(this@CreateWorkflowActivity)
            adapter.dataset=it!!
            val callback= ItemMoveCallback(this@CreateWorkflowActivity,ItemTouchHelper.UP.or(ItemTouchHelper.DOWN),0)
            touchHelper= ItemTouchHelper(callback)
            touchHelper?.attachToRecyclerView(rec_view)
            runOnUiThread{
                rec_view.adapter= adapter
            }

        }
        viewModel.getLiveBlockNames().observe(this,observer)

        val buttonContinue : View=  findViewById(R.id.button_continue)

        buttonContinue.setOnClickListener(this)
        button_cancel_workflow_creation.setOnClickListener(this)
        button_save_workflow.setOnClickListener(this)
        User.fetch(this, object: Listener<User, AuthError> {
            override fun onSuccess(p0: User) {
                return
            }
            override fun onError(p0: AuthError?) {
                return
            }
        })

    }

    override fun onClick(v: View?) {
        when(v) {
            button_continue -> {
                val workflowTitle=findViewById<TextView>(R.id.input_title_workflow).text.toString()
                val isUnique= viewModel.isUnique(workflowTitle)
                if(workflowTitle.isEmpty()){
                    Toast.makeText(this,"workflow name must not be empty",Toast.LENGTH_SHORT).show()
                }else {
                        if (!isUnique) {
                            Toast.makeText(this,"workflow name must be unique",Toast.LENGTH_SHORT).show()
                        } else {
                            viewModel.setName(workflowTitle)
                            val newIntent = Intent(this, CreateBlockActivity::class.java)
                            startActivityForResult(newIntent,1)
                        }
                    }
                }
            button_save_workflow -> {
                thread (start = true) {
                    viewModel.saveWorkflow()
                    setResult(Activity.RESULT_OK)
                    finish()
                }
            }
            button_cancel_workflow_creation -> startActivity(Intent(this, GeneralLoggedActivity::class.java))

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 1) {
            if(resultCode==Activity.RESULT_OK) {
                val blockType = data!!.extras!!.getString("block_type")

                when(blockType){
                    "Text to speech" -> {
                        val text= data!!.extras!!.get("text").toString()
                        viewModel.addOneArgBlock("Text to speech",text)
                    }
                    "FeedRss" -> {
                        val cardinality=data!!.extras!!.get("cardinality").toString().toShort()
                        val url=data!!.extras!!.get("FeedUrl").toString()
                        viewModel.addFilter(cardinality)
                        viewModel.addOneArgBlock("FeedRss",url)

                    }
                    "News" -> {
                        val cardinality=data!!.extras!!.get("cardinality").toString().toShort()
                        val news=data!!.extras!!.get("news").toString()
                        viewModel.addFilter(cardinality)
                        viewModel.addOneArgBlock("News",news)
                    }
                    "Pin" -> {
                        val pin= data!!.extras!!.get(("pin")).toString()
                        viewModel.addOneArgBlock("Pin",pin)
                    }
                    "Sport" -> {
                        val cardinality=data!!.extras!!.get("cardinality").toString().toShort()
                        val sport= data!!.extras!!.get(("sport")).toString()
                        viewModel.addFilter(cardinality)
                        viewModel.addOneArgBlock("Sport",sport)
                    }
                    "Crypto" -> {
                        val cardinality=data!!.extras!!.get("cardinality").toString().toShort()
                        val crypto= data!!.extras!!.get(("crypto")).toString()
                        viewModel.addFilter(cardinality)
                        viewModel.addOneArgBlock("Crypto",crypto)
                    }
                    "Borsa" -> {
                        val cardinality=data!!.extras!!.get("cardinality").toString().toShort()
                        val borsa= data!!.extras!!.get(("borsa")).toString()
                        viewModel.addFilter(cardinality)
                        viewModel.addOneArgBlock("Borsa", borsa)
                    }
                    "Twitter" -> {
                        val cardinality=data!!.extras!!.get("cardinality").toString().toShort()
                        val twit= data!!.extras!!.get(("screenName")).toString()
                        viewModel.addFilter(cardinality)
                        viewModel.addOneArgBlock("Twitter", twit)
                    }
                    "TwitterReadUserTimeline" -> {
                        val cardinality = data!!.extras!!.get("cardinality").toString().toShort()
                        val username =  data!!.extras!!.get("username").toString()
                        viewModel.addFilter(cardinality)
                        viewModel.addOneArgBlock("Twitter",username)
                    }
                }
            }

        }

    }

    fun notifyDeleteBlockInteraction(position: Int) {
        val builder= android.support.v7.app.AlertDialog.Builder(ContextThemeWrapper(this@CreateWorkflowActivity,R.style.AlertDialogCustom))
        val confirmDeletion={
                _: DialogInterface, _: Int -> viewModel.removeBlockAt(position)
        }
        val cancelDeletion= {
                _: DialogInterface, _:Int ->
        }
        with(builder) {
            setTitle("Delete Block")
            setPositiveButton("Confirm", confirmDeletion)
            setNegativeButton("Cancel", cancelDeletion)
        }
        builder.show()
    }

    fun swapItems(fromPosition:Int, toPosition:Int) {
        val mAdapter= rec_view.adapter as BlockViewAdapter
        mAdapter.swapItems(fromPosition,toPosition)
        viewModel.swapItems(fromPosition,toPosition)
    }
}