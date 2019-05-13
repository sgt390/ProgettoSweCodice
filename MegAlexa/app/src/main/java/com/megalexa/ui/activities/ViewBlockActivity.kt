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
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.ContextThemeWrapper
import android.view.View
import android.widget.EditText
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
import kotlinx.android.synthetic.main.activity_view_block.*

class ViewBlockActivity:AppCompatActivity(), View.OnClickListener {
    companion object {
        private lateinit var viewModel : WorkflowViewModel
    }
    private lateinit var rec_view: RecyclerView
    var touchHelper:ItemTouchHelper?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_block)
        supportActionBar?.hide()
        val extras :Bundle? = intent.extras
        val title: String?= extras!!.getString("WORKFLOW_NAME")

        if(title!= null) {
            workflow_title.text= title
        }
        
        rec_view=findViewById(R.id.recyclerView_addedBlocksView)
        rec_view.layoutManager= LinearLayoutManager(this)

        val factory= InjectorUtils.provideWorkflowViewModelFactory(title!!)

        viewModel = ViewModelProviders.of(this,factory).get(WorkflowViewModel::class.java)
        viewModel.setFromExistingWorkflow(title)

        val observer = Observer<ArrayList<String>>{
            val adapter = BlockViewAdapter(this@ViewBlockActivity)
            adapter.dataset=it!!
            val callback= ItemMoveCallback(this@ViewBlockActivity,ItemTouchHelper.UP.or(ItemTouchHelper.DOWN),ItemTouchHelper.RIGHT)
            touchHelper= ItemTouchHelper(callback)
            touchHelper?.attachToRecyclerView(rec_view)
            runOnUiThread{
                rec_view.adapter= adapter
            }
        }
        val errObserver = Observer<String>{
            if(it != "")
                Toast.makeText(this, it,Toast.LENGTH_LONG).show()
        }
        viewModel.getLiveError().observe(this,errObserver)
        viewModel.getLiveBlockNames().observe(this,observer)

        button_confirm_modification.setOnClickListener(this)
        button_add_blockView.setOnClickListener(this)
        button_cancel_modify.setOnClickListener(this)
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

            button_confirm_modification -> {
                if(viewModel.workflowIsValid()) {
                    val name= findViewById<TextView>(R.id.workflow_title)
                    viewModel.setName(name.text.toString())
                    viewModel.updateWorkflow()
                    setResult(Activity.RESULT_OK)
                    finish()
                }
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


        val builder= AlertDialog.Builder(ContextThemeWrapper(this@ViewBlockActivity,R.style.AlertDialogCustom))
        val confirmDeletion={
                _: DialogInterface, _: Int -> viewModel.removeBlockAt(position)
        }
        val cancelDeletion= {
                _:DialogInterface,_:Int -> viewModel.cancelPreviousIntent()
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

    fun startViewListActivity(position: Int) {
        val intent = Intent(this, ViewBlockListActivity::class.java)
        intent.putExtra("blockPosition", position)
        intent.putExtra("WORKFLOW_NAME", workflow_title.text)
        startActivityForResult(intent, 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 1 && resultCode==Activity.RESULT_OK) {
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
                    "TwitterHashtag" -> {
                        val cardinality=data!!.extras!!.get("cardinality").toString().toShort()
                        val twit= data!!.extras!!.get(("hashtag")).toString()
                        viewModel.addFilter(cardinality)
                        viewModel.addOneArgBlock("TwitterHashtag", twit)
                    }
                    "TwitterHomeTL" -> {
                        val cardinality = data!!.extras!!.get("cardinality").toString().toShort()
                        //val username =  data!!.extras!!.get("username").toString()
                        viewModel.addFilter(cardinality)
                        viewModel.addOneArgBlock("TwitterHomeTL", "")
                    }
                    "TwitterUserTL" -> {
                        val cardinality=data!!.extras!!.get("cardinality").toString().toShort()
                        val user = data!!.extras!!.get(("screenName")).toString()
                        viewModel.addFilter(cardinality)
                        viewModel.addOneArgBlock("TwitterUserTL",user)
                    }
                    "WriteTwitter" -> {
                        viewModel.addOneArgBlock("TwitterWrite","")
                    }
                    "Weather" -> {
                        val city= data!!.extras!!.get("city").toString()
                        viewModel.addOneArgBlock("Weather",city)
                    }
                    "List" -> {
                        viewModel.addOneArgBlock("List", "")
                    }
                }
        }
    }
}