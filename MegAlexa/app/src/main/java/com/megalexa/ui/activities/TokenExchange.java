package com.megalexa.ui.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import com.google.api.services.calendar.CalendarScopes;
import com.megalexa.R;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TokenExchange extends AppCompatActivity {

    private static final int RC_SIGN_IN = 0x100;
    private Button button;
    private TextView googleId;
    private TextView keycloakToken;
    private GoogleSignInClient mGoogleSignInClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token_exchange);
        button = findViewById(R.id.button);
        googleId = findViewById(R.id.google_id);
        keycloakToken = findViewById(R.id.keycloak_token);

        button.setOnClickListener(event -> {
            startExchange();
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.

            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                exchangeTokens(account);
            } catch (ApiException e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            }

        }
    }


    private void startExchange() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_idtwo))
                .requestScopes(new Scope(Scopes.PROFILE), new Scope(Scopes.PLUS_ME), new Scope(Scopes.EMAIL), new Scope(CalendarScopes.CALENDAR))
                .requestEmail().requestServerAuthCode(getString(R.string.default_web_client_idtwo), false)
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
        return;

    }


    private void exchangeTokens(GoogleSignInAccount account) {
        try {
            String token = account.getIdToken();
            String authCode = account.getServerAuthCode();
            Log.i("TOKEN", token);
            this.googleId.setText(token);
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            OkHttpClient client = builder.build();


            FormBody requestBody = new FormBody.Builder()
                    .add("client_id", getString(R.string.default_web_client_idtwo))
                    .add("client_secret",getString(R.string.client_secret))
                    .add("grant_type", "authorization_code")
                    .add("code",authCode)
                    .add("requested_token_type", "urn:ietf:params:oauth:token-type:refresh_token")
                    .add("redirect_uri","https://megalexa-1556132707047.firebaseapp.com/__/auth/handler")
                    .build();

            Request request = new Request.Builder()
                    .url("https://www.googleapis.com/oauth2/v4/token")
                    .post(requestBody)
                    .build();

/*   private val task= {object : AsyncTask<Void,Void,Response>() {


            override fun doInBackground(vararg params: Void?): Response {
                try {
                    //TRADURRE QUA
                }catch(e: IOException) {
                    Log.e("ERROR",e.message,e)
                    return null
                }
            }

            override fun onPostExecute(result: Response?) {
            super.onPostExecute(result)
                //TRADURRE QUA
            }



        }

    }
*/
            AsyncTask<Void, Void, Response> task = new AsyncTask<Void, Void, Response>() {
                @Override
                protected Response doInBackground(Void[] objects) {
                    try {
                        return client.newCall(request).execute();
                    } catch (IOException e) {
                        Log.e("ERROR", e.getMessage(), e);
                        return null;
                    }


                }

                @Override
                protected void onPostExecute(Response o) {
                    super.onPostExecute(o);
                    try {
                        if (o != null) {
                            String body = o.body().string();
                            Toast.makeText(TokenExchange.this, body, Toast.LENGTH_LONG).show();
                            Log.i("RESULT", body);
                            keycloakToken.setText(body);
                            o.body().close();
                        }
                    } catch (IOException e) {
                        Toast.makeText(TokenExchange.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        Log.e("ERROR", e.getMessage(), e);
                    }
                }
            }.

                    execute((Void) null);


        } catch (
                Exception e)

        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            Log.e("ERROR", e.getMessage(), e);
        }
    }
}