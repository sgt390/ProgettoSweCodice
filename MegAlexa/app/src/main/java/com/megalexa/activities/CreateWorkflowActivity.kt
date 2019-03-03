package com.megalexa.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.TextView
import com.amazon.identity.auth.device.AuthError
import com.amazon.identity.auth.device.api.Listener
import com.amazon.identity.auth.device.api.authorization.User
import com.megalexa.R
import com.megalexa.viewModel.ViewModelMain
import kotlinx.android.synthetic.main.activity_create_workflow.*
import kotlinx.android.synthetic.main.activity_view_block.*
import kotlin.concurrent.thread

class CreateWorkflowActivity: AppCompatActivity(), View.OnClickListener {

    companion object {
        private var viewModel : ViewModelMain = ViewModelMain()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_workflow)

        val buttonContinue : View=  findViewById(R.id.button_continue)

        buttonContinue.setOnClickListener(this)
        button_cancel_workflow_creation.setOnClickListener(this)
        button_save_workflow.setOnClickListener(this)
        User.fetch(this, object: Listener<User, AuthError> {
            override fun onSuccess(p0: User) {
                viewModel.setUser(p0)
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
                    val isPresent = viewModel.haveUserWorkflowName(workflowTitle)
                    runOnUiThread {
                        if (isPresent) {
                            Log.d("Stupido utente", "Non ti accorgi che hai gia questo nome")
                        } else {
                            var newIntent : Intent = Intent(this, CreateBlockActivity::class.java)
                            //newIntent.putExtra("workflowName", findViewById<TextView>(R.id.input_title_workflow).text.toString())
                            startActivity(newIntent)
                        }
                    }
                }
            }
            button_save_workflow -> {
                Log.d("Ci sono", "Ci passo")
                thread (start = true) {
                    viewModel.saveWorkflow(findViewById<TextView>(R.id.input_title_workflow).text.toString())
                    runOnUiThread{
                        startActivity(Intent(this, GeneralLoggedActivity::class.java))
                    }
                }
            }
            button_cancel_workflow_creation -> startActivity(Intent(this, GeneralLoggedActivity::class.java))

        }
    }
}