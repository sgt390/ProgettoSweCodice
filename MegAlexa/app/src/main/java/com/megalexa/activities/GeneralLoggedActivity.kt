package com.megalexa.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import android.widget.Button
import com.megalexa.R
import kotlinx.android.synthetic.main.activity_workflow.*
import android.content.Intent
import android.R.id
import android.view.Menu
import com.amazon.identity.auth.device.AuthError
import com.amazon.identity.auth.device.api.Listener
import com.amazon.identity.auth.device.api.authorization.AuthorizationManager
import kotlinx.android.synthetic.main.activity_general_logged.*
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.NullPointerException


class GeneralLoggedActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_general_logged)
        val navigationView  : View = findViewById(R.id.nav_view)
        navigationView.bringToFront()
        setSupportActionBar(toolbar)
        val toogle = ActionBarDrawerToggle(
            this,  drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toogle)
        toogle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
        add_workflow.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                startActivity(Intent(this@GeneralLoggedActivity, CreateWorkflowActivity::class.java))
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
