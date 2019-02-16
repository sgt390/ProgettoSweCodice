package com.megalexa.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.megalexa.R
import kotlinx.android.synthetic.main.activity_create_workflow.*

class CreateWorkflowActivity: AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_workflow)

        button_add_block.setOnClickListener(this)
        button_cancel_workflow_creation.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v) {
            button_add_block -> startActivity(Intent(this, CreateBlockActivity::class.java))
            button_cancel_workflow_creation -> startActivity(Intent(this, GeneralLoggedActivity::class.java))
            //TODO button_save_workflow ->
        }
    }
}