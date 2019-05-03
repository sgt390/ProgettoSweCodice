package com.megalexa.ui.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.transition.TransitionManager
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.*
import com.megalexa.R
import com.megalexa.ui.fragments.TwitterAnotherUserFragment
import com.megalexa.ui.fragments.TwitterFragment
import com.megalexa.ui.fragments.TwitterReadTimeLineUser
import com.megalexa.ui.fragments.WriteTweetFragment
import com.megalexa.util.view.FragmentClickListener
import com.twitter.sdk.android.core.*
import com.twitter.sdk.android.core.TwitterException
import com.twitter.sdk.android.core.identity.TwitterLoginButton
import com.twitter.sdk.android.core.models.User
import kotlinx.android.synthetic.main.activity_twitter.*

class TwitterActivity : AppCompatActivity(), FragmentClickListener {

    lateinit var  twitterLoginButton : TwitterLoginButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_twitter)
        val user = findViewById<LinearLayout>(R.id.userTwitter)
        val anotherUser = findViewById<LinearLayout>(R.id.homeTwitter)
        val hashtag = findViewById<LinearLayout>(R.id.SearchHashtag)
        val writeTweet = findViewById<LinearLayout>(R.id.writeTweet)
        val exitButton = findViewById<LinearLayout>(R.id.exitTwitter)
        val cancelButton = findViewById<Button>(R.id.button_cancel_twitter)

        user.setOnClickListener {
            val fragment = TwitterReadTimeLineUser()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container_fragment, fragment).addToBackStack("").commit()
            if(!isUserAuthenticated())  showLoginPopup()
        }
        anotherUser.setOnClickListener {
            val fragment = TwitterAnotherUserFragment()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container_fragment, fragment).addToBackStack("").commit()
            //showLoginPopup()
        }
        hashtag.setOnClickListener {
            val fragment = TwitterFragment()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container_fragment, fragment).addToBackStack("").commit()
            //showLoginPopup()
        }
        writeTweet.setOnClickListener {
            val fragment = WriteTweetFragment()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container_fragment, fragment).addToBackStack("").commit()
            if(!isUserAuthenticated())  showLoginPopup()
        }
        exitButton.setOnClickListener {
            if(isUserAuthenticated()) {
                TwitterCore.getInstance().sessionManager.clearActiveSession()
                showLoginPopup()
            }
        }
        cancelButton.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }

    override fun onFragmentClick(sender: Fragment) {
        if(sender is TwitterAnotherUserFragment){
            val anotherUser = sender.getUserName()
            Toast.makeText(this,"$anotherUser selected",Toast.LENGTH_SHORT).show()
            val intent = Intent(this, CreateBlockActivity::class.java)
            intent.putExtra("cardinality", sender.getCardinality())
            intent.putExtra("block_type", "TwitterUserTL")
            intent.putExtra("username", anotherUser)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }else if (sender is TwitterFragment){
            val hashtag = sender.getTwit()
            Toast.makeText(this,hashtag,Toast.LENGTH_SHORT).show()
            val intent = Intent(this,CreateBlockActivity::class.java)
            intent.putExtra("cardinality",sender.getCardinality())
            intent.putExtra("block_type", "TwitterHashtag")
            intent.putExtra("screenName",hashtag)
            setResult(Activity.RESULT_OK,intent)
            finish()
        }else if (sender is TwitterReadTimeLineUser){
            Toast.makeText(this, "Twitter read user timeline", Toast.LENGTH_SHORT).show()
            val intent = Intent(this,CreateBlockActivity::class.java)
            intent.putExtra("cardinality", sender.getCardinality())
            intent.putExtra("block_type", "TwitterHomeTL")
            setResult(Activity.RESULT_OK,intent)
            finish()
        }else if (sender is WriteTweetFragment){
            Toast.makeText(this, "New Tweet added", Toast.LENGTH_SHORT).show()
            val intent = Intent(this,CreateBlockActivity::class.java)
            intent.putExtra("block_type", "WriteTweet")
            setResult(Activity.RESULT_OK,intent)
            finish()
        }
    }

    private fun showLoginPopup() {
        val inflater: LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.twitter_login_popup, null)
        val popupWindow = PopupWindow(
            view, // Custom view to show in popup window
            LinearLayout.LayoutParams.MATCH_PARENT, // Width of popup window
            170 // Window height
        )
        // Settings window popup
        popupWindow.setBackgroundDrawable(ColorDrawable(Color.WHITE)) //Background color
        popupWindow.isFocusable = true //for click EditText
        popupWindow.setOnDismissListener {
            //supportFragmentManager.popBackStackImmediate()
        }
        twitterLoginButton = view.findViewById(R.id.login_twitter_button)
        val x = 5
        twitterLoginButton.callback = object : Callback<TwitterSession>() {
            override fun success(result: Result<TwitterSession>) {
                Log.d("Twitter", "Login eseguito")
                popupWindow.dismiss()
            }

            override fun failure(e: TwitterException) {
                Log.d("Twitter", "Login fallito")
            }
        }
        val twitterApiClient = TwitterCore.getInstance().apiClient
        val call = twitterApiClient.accountService.verifyCredentials(true, false, true)
        call.enqueue(object : Callback<User>() {
            override fun success(result: Result<User>?) {
                val user = result!!.data
                val x = 5
            }

            override fun failure(exception: TwitterException?) {    }
        })

        // Finally, show the popup window on app
        TransitionManager.beginDelayedTransition(twitter_activity)
        popupWindow.showAtLocation(
            twitter_activity, // Location to display popup window
            Gravity.CENTER_HORIZONTAL, // Exact position of layout to display popup
            0, // X offset
            0 // Y offset
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        twitterLoginButton.onActivityResult(requestCode, resultCode, data)
    }

    /**
     * Check if user is authenticated.
     *
     * @return true if authenticated
     */
    private fun isUserAuthenticated(): Boolean {
        return TwitterCore.getInstance().sessionManager.activeSession != null
    }

}


