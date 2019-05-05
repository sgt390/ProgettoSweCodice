package com.megalexa.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.Scope
import com.google.api.client.extensions.android.http.AndroidHttp
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.services.gmail.Gmail
import com.google.api.services.sheets.v4.SheetsScopes
import com.megalexa.R
import com.megalexa.util.ApplicationContextProvider.Companion.context


class GoogleActivity : AppCompatActivity() {
    companion object {
        private const val REQUEST_SIGN_IN = 1
    }

     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mail_fragment_layout)

        requestSignIn(this)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                GoogleSignIn.getSignedInAccountFromIntent(data)
                    .addOnSuccessListener { account ->
                        val scopes = listOf(SheetsScopes.SPREADSHEETS)
                        val credential = GoogleAccountCredential.usingOAuth2(context, scopes)
                        credential.selectedAccount = account.account

                        val jsonFactory = JacksonFactory.getDefaultInstance()
                        // GoogleNetHttpTransport.newTrustedTransport()
                        val httpTransport =  AndroidHttp.newCompatibleTransport()
                        val service = Gmail.Builder(httpTransport, jsonFactory, credential)
                            .setApplicationName(getString(R.string.app_name))
                            .build()

                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(context, "Errore nel Google Activity", Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }

    private fun requestSignIn(context: Context) {
        /*
        GoogleSignIn.getLastSignedInAccount(context)?.also { account ->
            Timber.d("account=${account.displayName}")
        }
         */

        val signInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            // .requestEmail()
            // .requestScopes(Scope(SheetsScopes.SPREADSHEETS_READONLY))
            .requestScopes(Scope(SheetsScopes.SPREADSHEETS))
            .build()
        val client = GoogleSignIn.getClient(context, signInOptions)

        startActivityForResult(client.signInIntent, REQUEST_SIGN_IN)
    }


    }
