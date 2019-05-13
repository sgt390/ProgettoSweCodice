package com.megalexa.ui.activities


import android.app.Activity
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
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

import com.google.android.gms.common.Scopes
import com.google.android.gms.common.api.Scope
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.megalexa.ui.fragments.CalendarFragment
import com.megalexa.ui.fragments.MailFragment
import com.megalexa.util.service.JSONConverter
import com.megalexa.util.view.FragmentClickListener
import kotlinx.android.synthetic.main.activity_google.*
import java.io.IOException
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.jetbrains.anko.contentView
import org.json.JSONObject
import org.mortbay.util.ajax.JSON


const val RC_SIGN_IN = 123

class GoogleActivity : AppCompatActivity() , FragmentClickListener {


    lateinit var mGoogleSignInClient : GoogleSignInClient
    var accessToken : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_google)
        val gmail = findViewById<LinearLayout>(R.id.Gmail)
        val calendar = findViewById<LinearLayout>(R.id.Calendar)
        val buttonCancel = findViewById<Button>(R.id.button_cancel_block)
        val buttonDisconnect = findViewById<Button>(R.id.disconnect_button)
        buttonDisconnect.visibility = View.GONE

        buttonDisconnect.setOnClickListener{
        signOut()
        buttonDisconnect.visibility = View.GONE
        }
        gmail.setOnClickListener {
                startExchange()
            buttonDisconnect.visibility = View.VISIBLE
            val fragment = MailFragment()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container_fragment, fragment).addToBackStack("").commit()
        }
        calendar.setOnClickListener {
                startExchange()
            buttonDisconnect.visibility = View.VISIBLE
            val fragment = CalendarFragment()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container_fragment, fragment).addToBackStack("").commit()
        }
        buttonCancel.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            this.finish()
        }

    }

    override fun onFragmentClick(sender: Fragment) {
        if(sender is MailFragment){
            val intent = Intent(this,CreateBlockActivity::class.java)
            intent.putExtra("cardinality",sender.getCardinality())
            intent.putExtra("block_type", "Email")
            intent.putExtra("token",accessToken)
            setResult(Activity.RESULT_OK,intent)
            finish()
        }else if (sender is CalendarFragment){
            val intent = Intent(this,CreateBlockActivity::class.java)
            intent.putExtra("cardinality",sender.getCardinality())
            intent.putExtra("block_type", "Calendar")
            intent.putExtra("token",accessToken)
            setResult(Activity.RESULT_OK,intent)
            finish()
        }
    }


    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                if (account != null) {
                    exchangeTokens(account)
                }
            } catch (e: ApiException) {
                Toast.makeText(this, e.message + "ApiException", Toast.LENGTH_LONG).show()
            }
        }
    }

    public override fun onStart() {
        super.onStart()
    }

    private fun startExchange() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.google_client_id))
            .requestScopes(
                Scope(Scopes.EMAIL),
                Scope(CalendarScopes.CALENDAR)
            )
            .requestEmail()
            .requestServerAuthCode(getString(R.string.google_client_id), false)
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
        return
    }

    private fun exchangeTokens(account: GoogleSignInAccount) : String  {

        try {
            val token = account.idToken
            val authCode = account.serverAuthCode
            val builder = OkHttpClient.Builder()
            val client = builder.build()

            val requestBody = FormBody.Builder()
                .add("client_id", getString(R.string.google_client_id))
                .add("client_secret", getString(R.string.google_client_secret))
                .add("grant_type", "authorization_code")
                .add("code", authCode!!)
                .add("requested_token_type", "urn:ietf:params:oauth:token-type:refresh_token")
                .add("redirect_uri", "https://megalexa-1556132707047.firebaseapp.com/__/auth/handler")
                .build()

            val request = Request.Builder()
                .url("https://www.googleapis.com/oauth2/v4/token")
                .post(requestBody)
                .build()



            val task = object : AsyncTask<Void, Void, Response>() {
                override fun doInBackground(objects: Array<Void>): Response? {
                    try {
                        return client.newCall(request).execute()
                    } catch (e: IOException) {
                        Log.e("ERROR", e.message, e)
                        return null
                    }


                }

                override fun onPostExecute(o: Response?) {
                    super.onPostExecute(o)
                    try {
                        if (o != null) {
                val jsonObject = JsonParser().parse(o.body()!!.string()).getAsJsonObject()
                            accessToken = jsonObject.get("access_token").asString
                            o.body()!!.close()
                        }
                    } catch (e: IOException) {
                        Toast.makeText(this@GoogleActivity, e.message+" IOException", Toast.LENGTH_LONG).show()
                        Log.e("ERROR", e.message, e)
                    }

                }
            }.execute(null as Void?)


        } catch (e: Exception) {
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
            Log.e("ERROR", e.message, e)
        }
        return accessToken
    }

    private fun signOut() {
        mGoogleSignInClient.signOut()
            .addOnCompleteListener(this, OnCompleteListener<Void> {
                // ...
            })
    }
    public fun getToken() : String{
        return accessToken
    }

}
