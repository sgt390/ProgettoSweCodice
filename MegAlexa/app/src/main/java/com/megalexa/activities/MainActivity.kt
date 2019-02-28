package com.megalexa.activities


import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.amazon.identity.auth.device.api.workflow.RequestContext
import com.amazon.identity.auth.device.api.authorization.*
import com.amazon.identity.auth.device.api.authorization.AuthCancellation
import com.amazon.identity.auth.device.AuthError
import com.amazon.identity.auth.device.api.Listener
import com.amazon.identity.auth.device.api.authorization.AuthorizeResult
import com.amazon.identity.auth.device.api.authorization.AuthorizeListener
import com.amazon.identity.auth.device.api.authorization.AuthorizationManager
import com.amazon.identity.auth.device.dataobject.Profile
import com.amazonaws.mobile.client.AWSMobileClient
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient
import com.megalexa.R
import com.megalexa.util.UserDO
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import kotlin.concurrent.thread
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    lateinit var requestContext: RequestContext
    companion object {
        private val TAG: String = this::class.java.simpleName
    }
    private var dynamoDBMapper: DynamoDBMapper? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestContext = RequestContext.create(this)
        AWSMobileClient.getInstance().initialize(this) {
            Log.d(TAG, "AWSMobileClient is initialized")
        }.execute()
        /*val client = AmazonDynamoDBClient(AWSMobileClient.getInstance().credentialsProvider)
        dynamoDBMapper = DynamoDBMapper.builder()
            .dynamoDBClient(client)
            .awsConfiguration(AWSMobileClient.getInstance().configuration)
            .build()*/
        requestContext.registerListener( object :
            AuthorizeListener(){
            /* Authorization was completed successfully. */
            override fun onSuccess(result : AuthorizeResult){
                val url = " https://m95485wij9.execute-api.us-east-1.amazonaws.com/beta/user/create/"
                val requestParam = "userID="+result.user.userId+"&name="+result.user.userName+"&email="+result.user.userEmail //URLEncoder.encode("userID", "UTF-8") + "=" + URLEncoder.encode(result.user.userId, "UTF-8") + "&" + URLEncoder.encode("email", "UTF-8") + "="  + URLEncoder.encode(result.user.userEmail, "UTF-8")+ "&" + URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(result.user.userName, "UTF-8")

                println(requestParam)

                //"userID="+result.user.userId+"&name="+result.user.userName+"&email="+result.user.userEmail

                val myURL = URL(url)

                with(myURL.openConnection() as HttpURLConnection) {
                    // optional default is GET
                    requestMethod = "POST"

                    val wr = OutputStreamWriter(getOutputStream());
                    wr.write(URLEncoder.encode(requestParam))
                    wr.flush();

                    println("URL : $url")
                    println("Response Code : $responseCode")

                    BufferedReader(InputStreamReader(inputStream)).use {
                        val response = StringBuffer()

                        var inputLine = it.readLine()
                        while (inputLine != null) {
                            response.append(inputLine)
                            inputLine = it.readLine()
                        }
                        it.close()
                        println("Response : $response")
                    }
                }

                /*var connection = myURL.openConnection() as HttpURLConnection
                connection.requestMethod = "POST"
                connection.outputStream.write(requestParam.toByteArray())
                try {
                    connection.connect()*/
                    Log.d("Connect", "Sono passato di qui")


                /*finally {
                    connection.disconnect();
                }*/
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
        var loginButton : View = findViewById(R.id.login_with_amazon)
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
        var scopes:  Array<Scope> = arrayOf(
            ProfileScope.profile(),
            ProfileScope.postalCode()
        )
        AuthorizationManager.getToken(this, scopes, object : Listener<AuthorizeResult, AuthError> {
            override fun onSuccess(result: AuthorizeResult) {
                if (result.accessToken != null) {
                    /* The user is signed in */
                    startActivity(Intent(this@MainActivity, GeneralLoggedActivity::class.java))
                } else {
                    /* The user is not signed in */
                }
            }
            override fun onError(ae: AuthError) {
                /* The user is not signed in */
            }
        })
    }


}