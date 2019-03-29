package com.megalexa.ui.activities

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.view.View
import com.megalexa.R
import kotlinx.android.synthetic.main.activity_workflow.*
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.amazon.identity.auth.device.AuthError
import com.amazon.identity.auth.device.api.Listener
import com.amazon.identity.auth.device.api.authorization.AuthorizationManager
import com.amazon.identity.auth.device.api.authorization.User
import com.megalexa.adapters.view.WorkflowViewAdapter
import kotlinx.android.synthetic.main.activity_general_logged.*
import com.megalexa.models.workflow.Workflow
import com.megalexa.util.InjectorUtils
import com.megalexa.viewModel.MegAlexaViewModel
import com.megalexa.viewModel.ViewModelMain


class GeneralLoggedActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    companion object {
        private lateinit var viewModel : MegAlexaViewModel
    }

    private lateinit var layoutManager: RecyclerView.LayoutManager
    private var listWorkflow= ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_general_logged)
        val factory= InjectorUtils.provideMegAlexaViewModelFactory()
        viewModel = ViewModelProviders.of(this,factory).get(MegAlexaViewModel::class.java)

        val recyclerView=findViewById<RecyclerView>(R.id.container_workflow)
        recyclerView.setHasFixedSize(true)
        layoutManager= LinearLayoutManager(this)
        recyclerView.layoutManager=layoutManager

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
                startActivity(Intent(this@GeneralLoggedActivity, CreateWorkflowActivity::class.java))
            }
        })
        User.fetch(this, object: Listener<User, AuthError>{
            override fun onSuccess(p0: User) {
                //TODO() FETCH AND SET WORKFLOW NAMES

                //viewModel.setUser(p0)
                //listWorkflow = viewModel.fetchWorkflow()
                runOnUiThread{
                    recyclerView.adapter= WorkflowViewAdapter(listWorkflow,this@GeneralLoggedActivity)
                }
            }
            override fun onError(p0: AuthError?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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

                    }
                })
                return true
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

}
