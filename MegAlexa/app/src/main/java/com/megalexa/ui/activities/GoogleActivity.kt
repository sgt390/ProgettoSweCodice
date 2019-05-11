package com.megalexa.ui.activities


import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.megalexa.R
import kotlinx.android.synthetic.main.mail_fragment_layout.*

import com.google.android.gms.tasks.OnCompleteListener
import com.google.api.client.json.jackson2.JacksonFactory


import com.google.api.services.calendar.CalendarScopes
import com.megalexa.util.ApplicationContextProvider


const val RC_SIGN_IN = 123

class GoogleActivity : AppCompatActivity() {
    companion object {
        private const val REQUEST_SIGN_IN = 1
        private val JSON_FACTORY = JacksonFactory.getDefaultInstance()
        private val SCOPES = listOf(CalendarScopes.CALENDAR)
        private val TOKENS_DIRECTORY_PATH = "tokens"
    }
    lateinit var googleSignInClient : GoogleSignInClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mail_fragment_layout)

        val options = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestServerAuthCode(ApplicationContextProvider.context!!.resources!!
                .getString(R.string.default_web_client_idtwo))
            .requestIdToken(ApplicationContextProvider.context!!.resources!!
                .getString(R.string.default_web_client_idtwo)).requestEmail().build()
        googleSignInClient = GoogleSignIn.getClient(this, options)


        ///////////////////////////////////////////////////////////////////////////////////////
        sign_in_button.visibility = View.VISIBLE
        tv_name.visibility = View.GONE
        sign_in_button.setSize(SignInButton.SIZE_STANDARD)
        sign_in_button.setOnClickListener{
            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }
        val acct = GoogleSignIn.getLastSignedInAccount(this)
        if (acct != null) {
            sign_in_button.visibility = View.GONE
            tv_name.text = acct.email
            Log.d("TOKEN",acct.idToken)
            tv_name.visibility = View.VISIBLE
//            Log.d("Tag",acct.idToken)
        }
        googleSignInClient.signOut()
            .addOnCompleteListener(this, OnCompleteListener<Void> {
                // ...
            })
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            sign_in_button.visibility = View.GONE
            if (account != null) {
                val ciao = account.idToken
                Log.d("tag", account.serverAuthCode)
                tv_name.text = account.displayName
            }
            tv_name.visibility = View.VISIBLE
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.

            sign_in_button.visibility = View.VISIBLE
            tv_name.text = ""
            tv_name.visibility = View.GONE

        }

    }
    private fun signOut() {

    }
}
