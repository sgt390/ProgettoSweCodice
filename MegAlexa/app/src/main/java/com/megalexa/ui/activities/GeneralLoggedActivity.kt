/*
 *
 *  File name: GeneralLoggedActivity
 *  Version: 1.0.0
 *  Date: 2019-03-01
 *  Author: Ludovico Brocca
 *  License:
 *  History:
 *  Author           || Date            || Description
 *  Mirko Franco     || 2019-02-23      || File created
 *  Matteo Depascale || 2019-03-07      || Verifying code
 *
 */

package com.megalexa.ui.activities

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.ContextThemeWrapper
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.amazon.identity.auth.device.AuthError
import com.amazon.identity.auth.device.api.Listener
import com.amazon.identity.auth.device.api.authorization.AuthorizationManager
import com.amazon.identity.auth.device.api.authorization.User
import com.megalexa.R
import com.megalexa.ui.adapters.WorkflowViewAdapter
import com.megalexa.util.InjectorUtils
import com.megalexa.viewModel.MegAlexaViewModel
import kotlinx.android.synthetic.main.activity_general_logged.*
import kotlinx.android.synthetic.main.activity_workflow.*


class GeneralLoggedActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    companion object {
        private lateinit var viewModel : MegAlexaViewModel
    }
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_general_logged)
        supportActionBar?.hide()
        val factory= InjectorUtils.provideMegAlexaViewModelFactory()
        viewModel = ViewModelProviders.of(this,factory).get(MegAlexaViewModel::class.java)

        val observer = Observer<ArrayList<String>>{
            val adapter = WorkflowViewAdapter(it!!, this@GeneralLoggedActivity)
            runOnUiThread{
                recyclerView.adapter= adapter
            }
        }
        val errObserver = Observer<String>{
            if(it != "")
                Toast.makeText(this, it,Toast.LENGTH_LONG).show()
        }

        viewModel.getLiveWorkflowNames().observe(this,observer)
        viewModel.getLiveError().observe(this, errObserver)

        recyclerView=findViewById(R.id.container_workflow)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager= LinearLayoutManager(this)

        val navigationView  : View = findViewById(R.id.nav_view)
        navigationView.bringToFront()
        setSupportActionBar(toolbar)
        val toogle = ActionBarDrawerToggle(
            this,  drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toogle)
        toogle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
        add_workflow.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(this@GeneralLoggedActivity, CreateWorkflowActivity::class.java)
                startActivityForResult(intent,1)
            }
        })
        User.fetch(this, object: Listener<User, AuthError>{
            override fun onSuccess(p0: User) {
                viewModel.setUser(p0)
                viewModel.loadAppContext()
            }
            override fun onError(p0: AuthError?) {
                return
            }
        })
    }

    override fun onBackPressed() {
        if(drawer_layout.isDrawerOpen(GravityCompat.START)){
            drawer_layout.closeDrawer(GravityCompat.START)
        }else{
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_quit -> {
                AuthorizationManager.signOut(applicationContext, object: Listener<Void, AuthError> {
                    override fun onSuccess(response: Void?) {

                        startActivity(Intent(this@GeneralLoggedActivity, MainActivity::class.java))
                    }

                    override fun onError(authError: AuthError) {
                        return
                    }
                })
                return true
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onResume() {
        super.onResume()
        return
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 1) {
            if(resultCode== Activity.RESULT_OK) {
                Toast.makeText(this@GeneralLoggedActivity,"Your workflow was saved", Toast.LENGTH_SHORT).show()
                viewModel.cancelPreviousIntent()
            }

            if(resultCode == Activity.RESULT_CANCELED)
                Toast.makeText(this@GeneralLoggedActivity,"Workflow creation canceled", Toast.LENGTH_SHORT).show()
        }

        if(requestCode == 5) {
            if(resultCode== Activity.RESULT_OK) {
                Toast.makeText(this@GeneralLoggedActivity,"Your workflow was modified", Toast.LENGTH_SHORT).show()
                viewModel.cancelPreviousIntent()
            }

            if(resultCode == Activity.RESULT_CANCELED)
                Toast.makeText(this@GeneralLoggedActivity,"Workflow modification canceled", Toast.LENGTH_SHORT).show()
        }

    }

    fun passIntentForResult(intent:Intent) {
        startActivityForResult(intent,5)
    }

    fun notifyDeleteInteraction(position :Int) {

        val builder= android.support.v7.app.AlertDialog.Builder(ContextThemeWrapper(this@GeneralLoggedActivity,R.style.AlertDialogCustom))
        val confirmDeletion={
              _: DialogInterface, _: Int -> viewModel.removeWorkflow(position)
          }
          val cancelDeletion= {
              _:DialogInterface,_:Int ->
          }

            with(builder) {

                setTitle("Delete workflow")
                setPositiveButton("Confirm", confirmDeletion)
                setNegativeButton("Cancel", cancelDeletion)
            }

            builder.show()
        }
}
