package com.megalexa.ui.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import com.amazon.identity.auth.device.AuthError
import com.amazon.identity.auth.device.api.Listener
import com.amazon.identity.auth.device.api.authorization.User
import com.megalexa.R
import com.megalexa.ui.adapters.BlockViewAdapter
import com.megalexa.util.InjectorUtils
import com.megalexa.viewModel.WorkflowViewModel
import kotlinx.android.synthetic.main.activity_create_workflow.*
import kotlinx.android.synthetic.main.activity_view_block.*

class ViewBlockActivity:AppCompatActivity(), View.OnClickListener {
    companion object {
        private lateinit var viewModel : WorkflowViewModel
    }
    private lateinit var rec_view: RecyclerView

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
        workflow_title.text= title
        val factory= InjectorUtils.provideWorkflowViewModelFactory(title!!)
        viewModel = ViewModelProviders.of(this,factory).get(WorkflowViewModel::class.java)
        viewModel.setFromExistingWorkflow()
        val observer = Observer<ArrayList<String>>{
            val adapter = BlockViewAdapter(it!!, applicationContext)
            runOnUiThread{
                rec_view.adapter= adapter
            }
        }
        viewModel.refreshBlocks()
        viewModel.getLiveBlockNames().observe(this,observer)
        button_add_blockView.setOnClickListener(this)
        button_cancel_workflow_creationView.setOnClickListener(this)
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
            button_save_workflowView -> viewModel.updateWorkflow()
            button_continue -> startActivityForResult(Intent(this, CreateBlockActivity::class.java),1)
            button_cancel_workflow_creation -> startActivityForResult(Intent(this, GeneralLoggedActivity::class.java),9)
        }
    }

}