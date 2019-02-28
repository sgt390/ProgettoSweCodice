package com.megalexa.activities

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
import android.R.id
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import com.amazon.identity.auth.device.AuthError
import com.amazon.identity.auth.device.api.Listener
import com.amazon.identity.auth.device.api.authorization.AuthorizationManager
import com.amazon.identity.auth.device.api.authorization.User
import com.amazonaws.mobile.client.AWSMobileClient
import com.megalexa.adapters.view.WorkflowViewAdapter
import com.megalexa.util.UserDO
import kotlinx.android.synthetic.main.activity_general_logged.*
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBQueryExpression
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient
import com.amazonaws.services.dynamodbv2.model.AttributeValue
import com.megalexa.util.WorkflowDO
import kotlin.concurrent.thread


class GeneralLoggedActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private  var dynamoDBMapper : DynamoDBMapper? = null
    private lateinit var userID : String
    private lateinit var workflowNames: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_general_logged)

        //must be changed to load real workflow names
        workflowNames= debugWorkflowNames()
        container_workflow.layoutManager=LinearLayoutManager(this)
        container_workflow.adapter= WorkflowViewAdapter(workflowNames,this)

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
       /* val client = AmazonDynamoDBClient(AWSMobileClient.getInstance().credentialsProvider)
        dynamoDBMapper = DynamoDBMapper.builder()
            .dynamoDBClient(client)
            .awsConfiguration(AWSMobileClient.getInstance().configuration)
            .build()*/
        User.fetch(this, object: Listener<User, AuthError>{
            override fun onSuccess(p0: User) {
                userID = p0.userId
                //queryWorkflow()
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

    /*private fun queryWorkflow(){
        thread(start = true){
            val item = WorkflowDO()
            item.setUserID(userID)
            item.setID(0)
            val queryExpression = DynamoDBQueryExpression<WorkflowDO>()
            queryExpression.indexName = "userID"
            queryExpression.withHashKeyValues(item)
            queryExpression.withConsistentRead(false)
            val result = dynamoDBMapper?.query(WorkflowDO::class.java, queryExpression)
            runOnUiThread {
                for (value in result!!) {
                    Log.d("Workflow", "ID: " + value.getID() +  " Nome: " + value.getName() + "userID: " + value.getUserID() )
                }
            }
        }
    }*/

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


    /**retrieves the names of the workflow for the given user
     * @param User identifies the user that needs to display the workflow
     */
    fun fetchWorkflowNames(User: UserDO){

        TODO()
    }


    private fun debugWorkflowNames():ArrayList<String>{

        return arrayListOf("Workflow1","Workflow2","Workflow3","Workflow4","Workflow5")

    }






}
