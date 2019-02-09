package com.megalexa

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.amazon.identity.auth.device.api.workflow.RequestContext
import com.amazon.identity.auth.device.AuthError
import android.R.id
import android.view.View
import com.amazon.identity.auth.device.api.authorization.*


class MainActivity : AppCompatActivity() {

    //private var requestContext: RequestContext? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*requestContext = RequestContext.create(this);
        requestContext!!.registerListener(object : AuthorizeListener() {

            /* Authorization was completed successfully. */
            override fun onSuccess(result: AuthorizeResult) {
                /* Your app is now authorized for the requested scopes */
                print("onSuccess")
            }

            /* There was an error during the attempt to authorize the
        application. */
            override fun onError(ae: AuthError) {
                /* Inform the user of the error */
                print("onError")
            }

            /* Authorization was cancelled before it could be completed. */
            override fun onCancel(cancellation: AuthCancellation) {
                /* Reset the UI to a ready-to-login state */
                print("onCancel")
            }
        })
        val loginButton : View = findViewById(R.id.login_with_amazon)
        loginButton.setOnClickListener(View.OnClickListener() {
            fun onClick(v: View) {
                AuthorizationManager.authorize(AuthorizeRequest
                        .Builder(requestContext)
                    .addScopes(ProfileScope.profile(), ProfileScope.postalCode())
                    .build());
            }})*/

    }

    /*override fun onResume() {
        super.onResume()
        requestContext!!.onResume()
    }*/
}
