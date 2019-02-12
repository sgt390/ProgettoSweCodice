package com.megalexa


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.amazon.identity.auth.device.api.workflow.RequestContext
import com.amazon.identity.auth.device.api.authorization.*
import com.amazon.identity.auth.device.api.authorization.AuthCancellation
import com.amazon.identity.auth.device.AuthError
import com.amazon.identity.auth.device.api.Listener
import com.amazon.identity.auth.device.api.authorization.AuthorizeResult
import com.amazon.identity.auth.device.api.authorization.AuthorizeListener
import com.amazon.identity.auth.device.api.authorization.AuthorizationManager



class MainActivity : AppCompatActivity() {

    lateinit var requestContext: RequestContext
    lateinit var result : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        result = findViewById(R.id.result_login)
        requestContext = RequestContext.create(this)
        requestContext.registerListener( object :
            AuthorizeListener(){
            /* Authorization was completed successfully. */
            override fun onSuccess(result : AuthorizeResult){
                /*The app in authorized for the requested scopes */
                this@MainActivity.result.text = "onSuccess"
            }
            /* There was an error during the attempt to authorize the application. */
            override fun onError(ae : AuthError) {
                /* Inform the user of the error */
                this@MainActivity.result.text = "onError"
            }
            /* Authorization was cancelled before it could be completed. */
            override fun onCancel(cancellation : AuthCancellation){
                /* Reset the UI to a ready-to-login state */
                this@MainActivity.result.text = "onCancel"
            }

        }
        );
        var loginButton : View = findViewById(R.id.login_with_amazon)
        loginButton.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v : View){
                AuthorizationManager.authorize(
                    AuthorizeRequest.Builder(requestContext).addScopes(ProfileScope.profile(), ProfileScope.postalCode()).build()
                )
            }
        })
        var logoutButton : View = findViewById(R.id.logout_button)
        logoutButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v : View){
                AuthorizationManager.signOut(applicationContext, object : Listener<Void, AuthError>{

                    override fun onSuccess(response : Void ){
                        this@MainActivity.result.text = "Logged out";
                    }

                    override fun onError(authError: AuthError){
                        this@MainActivity.result.text = "Error log out"
                    }
                })
            }
        })
    }

    override fun onResume(){
        super.onResume()
        requestContext.onResume()
    }

    override fun onStart() {
        super.onStart()
        var scopes:  Array<Scope> = arrayOf(
            ProfileScope.profile(),
            ProfileScope.postalCode()
        )
        AuthorizationManager.getToken(this, scopes, object : Listener<AuthorizeResult, AuthError> {
            override fun onSuccess(result: AuthorizeResult) {
                if (result.accessToken != null) {
                    /* The user is signed in */
                    this@MainActivity.result.text = "Signed in"
                } else {
                    /* The user is not signed in */
                    this@MainActivity.result.text = "Not Signed in"
                }
            }
            override fun onError(ae: AuthError) {
                /* The user is not signed in */
            }
        })
    }


}