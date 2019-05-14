/*
 *
 *  File name:
 *  Version:
 *  Date:
 *  Author:
 *  License:
 *  History:
 *  Author        || Date            || Description
 * /
 *
 */

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
import android.text.Editable
import android.text.TextWatcher
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
import com.twitter.sdk.android.core.TwitterCore
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
        supportActionBar?.hide()
        val factory= InjectorUtils.provideWorkflowViewModelFactory("")
        viewModel = ViewModelProviders.of(this,factory).get(WorkflowViewModel::class.java)
        rec_view= findViewById(R.id.recyclerView_addedBlocksOnCreation)
        rec_view.layoutManager = LinearLayoutManager(this)
        val observer = Observer<ArrayList<String>>{
            val adapter = BlockViewAdapter(this@CreateWorkflowActivity)
            adapter.dataset=it!!
            val callback= ItemMoveCallback(this@CreateWorkflowActivity,ItemTouchHelper.UP.or(ItemTouchHelper.DOWN),ItemTouchHelper.RIGHT)
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


        val wNameView=findViewById<TextView>(R.id.input_title_workflow)

        wNameView.addTextChangedListener(object: TextWatcher{

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                return
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                return
            }

            override fun afterTextChanged(s: Editable?) {
                viewModel.setName(wNameView.text.toString())
            }

        })

        viewModel.getLiveError().observe(this,errObserver)
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
                    val workflowTitle=findViewById<TextView>(R.id.input_title_workflow).text.toString()
                    val isUnique= viewModel.isUnique(workflowTitle)
                    if(workflowTitle.isEmpty()){
                        Toast.makeText(this,"workflow name must not be empty",Toast.LENGTH_SHORT).show()
                    }else {
                        if(!isUnique) {
                            Toast.makeText(this,"workflow name must be unique",Toast.LENGTH_SHORT).show()
                        } else if(viewModel.workflowIsValid()) {
                            thread(start= true) {
                                viewModel.saveWorkflow()
                            }
                            TwitterCore.getInstance().sessionManager.clearActiveSession() //clear Twitter session
                            setResult(Activity.RESULT_OK)
                            finish()
                        }
                }
            }
            button_cancel_workflow_creation -> startActivity(Intent(this, GeneralLoggedActivity::class.java))

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 1 && resultCode==Activity.RESULT_OK ) {
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
                    "Email" -> {
                        val cardinality=data!!.extras!!.get("cardinality").toString().toShort()
                        val token = data!!.extras!!.get(("access_token")).toString()
                        val refreshToken = data!!.extras!!.get(("refresh_token")).toString()
                        viewModel.addFilter(cardinality)
                        viewModel.addTwoArgBlock("Email", token,refreshToken)

                    }

                    "Calendar" -> {
                        val cardinality=data!!.extras!!.get("cardinality").toString().toShort()
                        val token = data!!.extras!!.get(("access_token")).toString()
                        viewModel.addFilter(cardinality)
                        viewModel.addOneArgBlock("Calendar", token)

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
                _: DialogInterface, _:Int -> viewModel.cancelPreviousIntent()
        }
        with(builder) {
            setTitle("Delete block")
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