package com.megalexa.ui.activities

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.amazon.identity.auth.device.AuthError
import com.amazon.identity.auth.device.api.Listener
import com.amazon.identity.auth.device.api.authorization.User
import com.megalexa.R
import com.megalexa.ui.adapters.BlockViewAdapter
import com.megalexa.util.InjectorUtils
import com.megalexa.viewModel.MegAlexaViewModel
import com.megalexa.viewModel.WorkflowViewModel
import kotlinx.android.synthetic.main.activity_create_workflow.*
import org.json.JSONObject
import java.io.Serializable
import kotlin.concurrent.thread

class CreateWorkflowActivity: AppCompatActivity(), View.OnClickListener {

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
            val adapter = BlockViewAdapter(it!!, applicationContext)
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
                viewModel.refreshBlocks()
            }
            override fun onError(p0: AuthError?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

    }

    override fun onClick(v: View?) {
        when(v) {
            button_continue -> {
                thread (start = true) {
                    val workflowTitle=findViewById<TextView>(R.id.input_title_workflow).text.toString()

                    val isUnique= viewModel.isUnique(workflowTitle)
                    runOnUiThread {
                        if (!isUnique) {
                           Toast.makeText(this,"workflow name must be unique",Toast.LENGTH_SHORT).show()
                        } else {
                            viewModel.setName(workflowTitle)
                            val newIntent = Intent(this, CreateBlockActivity::class.java)
                            startActivityForResult(newIntent,1)
                        }
                    }

                }

            }
            button_save_workflow -> {
                thread (start = true) {
                    viewModel.saveWorkflow()
                    runOnUiThread{
                        startActivity(Intent(this, GeneralLoggedActivity::class.java))
                    }
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
                    //TODO() LET VIEWMODEL HANDLE THE ADDITION OF BLOCKS
                    "Text to speech" -> {
                        /*val block= BlockTextToSpeech(data!!.extras!!.get("text").toString())
                        blockList.add(block)
                        blocknames.add(block.getInformation())*/
                    }
                    "FeedRss" -> {

                      /*  val block=BlockFeedRss(data!!.extras!!.get("feedRss").toString())
                        blockList.add(block)
                        blocknames.add(block.getInformation())*/
                    }

                }
            }


        }

    }

}