package com.megalexa.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.megalexa.R
import kotlinx.android.synthetic.main.activity_create_block.*
import kotlinx.android.synthetic.main.recycler_view_item.*
import com.megalexa.fragments.RssFragment

class CreateBlockActivity: AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_block)

        button_cancel_block.setOnClickListener(this)




        /*rssBlock.setOnClickListener {
            // Get the text fragment instance
            val textFragment = RssFragment()

            // Get the support fragment manager instance
            val manager = supportFragmentManager

            // Begin the fragment transition using support fragment manager
            val transaction = manager.beginTransaction()

            // Replace the fragment on container
            transaction.replace(R.id.fragment_container,textFragment)
            transaction.addToBackStack(null)

            // Finishing the transition
            transaction.commit()
        }*/


    }

    override fun onClick(v: View?) {
        when(v) {
            button_cancel_block -> startActivity(Intent(this, CreateWorkflowActivity::class.java))
        }
    }
}