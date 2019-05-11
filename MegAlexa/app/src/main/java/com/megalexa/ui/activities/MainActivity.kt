package com.megalexa.ui.activities


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.ImageView
import android.widget.Toast
import com.amazon.identity.auth.device.api.workflow.RequestContext
import com.amazon.identity.auth.device.api.authorization.*
import com.amazon.identity.auth.device.api.authorization.AuthCancellation
import com.amazon.identity.auth.device.AuthError
import com.amazon.identity.auth.device.api.Listener
import com.amazon.identity.auth.device.api.authorization.AuthorizeResult
import com.amazon.identity.auth.device.api.authorization.AuthorizeListener
import com.amazon.identity.auth.device.api.authorization.AuthorizationManager
import com.megalexa.R
import com.megalexa.util.InjectorUtils
import com.megalexa.viewModel.MegAlexaViewModel

class MainActivity : AppCompatActivity() {

    lateinit var requestContext: RequestContext
    companion object {
        private lateinit var viewModel : MegAlexaViewModel
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        val factory= InjectorUtils.provideMegAlexaViewModelFactory()
        viewModel= ViewModelProviders.of(this,factory).get(MegAlexaViewModel::class.java)
        requestContext = RequestContext.create(this)
        requestContext.registerListener( object :
            AuthorizeListener(){
            /* Authorization was completed successfully. */
            override fun onSuccess(result : AuthorizeResult) {
                viewModel.setUser(result.user)
                viewModel.saveUser()
                startActivity(Intent(this@MainActivity, GeneralLoggedActivity::class.java))
            }
            /* There was an error during the attempt to authorize the application. */
            override fun onError(ae : AuthError) {
                /* Inform the user of the error */

            }
            /* Authorization was cancelled before it could be completed. */
            override fun onCancel(cancellation : AuthCancellation){
                /* Reset the UI to a ready-to-login state */

            }

        }
        )

        val logo= findViewById<ImageView>(R.id.imageView9)
        val animation = AlphaAnimation(0.0f , 1.0f)
        animation.duration=2400
        logo.animation=animation
        logo.startAnimation(animation)

        val errObserver = Observer<String>{
            if(it != "")
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }

        viewModel.getLiveError().observe(this,errObserver)

        val loginButton : View = findViewById(R.id.login_with_amazon)
        loginButton.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v : View){
                AuthorizationManager.authorize(
                    AuthorizeRequest.Builder(requestContext).addScopes(ProfileScope.profile(), ProfileScope.postalCode()).build()
                )
            }
        })
    }

    override fun onResume(){
        super.onResume()
        requestContext.onResume()
    }

    override fun onStart() {
        super.onStart()
        val scopes:  Array<Scope> = arrayOf(
            ProfileScope.profile(),
            ProfileScope.postalCode()
        )
        AuthorizationManager.getToken(this, scopes, object : Listener<AuthorizeResult, AuthError> {
            override fun onSuccess(result: AuthorizeResult) {
                Log.d("onStart", "Fuori")
                if (result.accessToken != null) {
                    /* The user is signed in */
                    startActivity(Intent(this@MainActivity, GeneralLoggedActivity::class.java))
                } else {
                    /* The user is not signed in */
                    return
                }
            }
            override fun onError(ae: AuthError) {
                return
            }
        })
    }



}