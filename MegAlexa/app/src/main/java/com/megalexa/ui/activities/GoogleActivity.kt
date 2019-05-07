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

        // Configure sign-in to request the user's ID, email address, and basic
// profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        //val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
          //  .requestIdToken(ApplicationContextProvider.context!!.resources!!.getString(R.string.client_Id))
            //.requestEmail()
            //.build()
        // Build a GoogleSignInClient with the options specified by gso.
        //val mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        ///////////////////////////////////////////////////////////////////////
        //      MY CODE FROM QUICKSTART


//               var HTTP_TRANSPORT =  com.google.api.client.http.javanet.NetHttpTransport()
//        val input = this::class.java.getResourceAsStream("/credentials.json")
//            ?: throw FileNotFoundException("Resource not found: /credentials.json")
//        val clientSecrets: GoogleClientSecrets = GoogleClientSecrets.load(JSON_FACTORY, InputStreamReader(input))
//        val flow = GoogleAuthorizationCodeFlow.Builder(
//            HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES
//        )
////            .setDataStoreFactory(FileDataStoreFactory(File(TOKENS_DIRECTORY_PATH)))
//            .setAccessType("offline")
//            .build()
//        println("client: " + flow.clientAuthentication)
//        val receiver: LocalServerReceiver = LocalServerReceiver.Builder().setPort(8888).build()
//        val Tok  = doAsyncResult { AuthorizationCodeInstalledApp(flow,receiver).authorize("user") }
        val options = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(ApplicationContextProvider.context!!.resources!!.getString(R.string.google_client_id)).requestEmail().build()
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
            tv_name.text = acct.displayName
            tv_name.visibility = View.VISIBLE
//            Log.d("Tag",acct.idToken)
        }
        /*mGoogleSignInClient.signOut()
            .addOnCompleteListener(this, OnCompleteListener<Void> {
                // ...
            })*/
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
                Log.d("tag", account.idToken)
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
