package com.megalexa.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.megalexa.R
import kotlinx.android.synthetic.main.activity_create_block.*
import kotlinx.android.synthetic.main.activity_create_workflow.*

class CreateBlockActivity: AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_block)

        button_cancel_block.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v) {
            button_cancel_block -> startActivity(Intent(this, CreateWorkflowActivity::class.java))
        }
    }
}