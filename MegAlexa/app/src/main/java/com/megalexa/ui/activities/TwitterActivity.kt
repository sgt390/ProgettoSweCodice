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
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.*
import com.megalexa.R
import com.megalexa.ui.fragments.TwitterAnotherUserFragment
import com.megalexa.ui.fragments.TwitterFragment
import com.megalexa.ui.fragments.TwitterReadTimeLineUser
import com.megalexa.ui.fragments.WriteTweetFragment
import com.megalexa.util.view.FragmentClickListener
import kotlinx.android.synthetic.main.activity_twitter.*

class TwitterActivity : AppCompatActivity(), FragmentClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_twitter)

        val user = findViewById<LinearLayout>(R.id.userTwitter)
        val anotherUser = findViewById<LinearLayout>(R.id.homeTwitter)
        val hashtag = findViewById<LinearLayout>(R.id.SearchHashtag)
        val writeTweet = findViewById<LinearLayout>(R.id.writeTweet)

        //showLoginPopup()
        /*
        * Sistemare layout fragment
        * e correggere un null nel popup*/
        user.setOnClickListener {
            val fragment = TwitterReadTimeLineUser()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container_fragment, fragment).addToBackStack("").commit()
        }
        anotherUser.setOnClickListener {
            val fragment = TwitterAnotherUserFragment()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container_fragment, fragment).addToBackStack("").commit()
        }
        hashtag.setOnClickListener {
            val fragment = TwitterFragment()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container_fragment, fragment).addToBackStack("").commit()
        }
        writeTweet.setOnClickListener {
            val fragment = WriteTweetFragment()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container_fragment, fragment).addToBackStack("").commit()
        }
    }

    override fun onFragmentClick(sender: Fragment) {
        if(sender is TwitterAnotherUserFragment){
            val anotherUser = sender.getUserName()
            Toast.makeText(this,anotherUser,Toast.LENGTH_SHORT).show()
            val intent = Intent(this,CreateBlockActivity::class.java)
            intent.putExtra("cardinality",sender.getCardinality())
            intent.putExtra("block_type", "TwitterUserTL")
            intent.putExtra("username",anotherUser)
            setResult(Activity.RESULT_OK,intent)
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
            intent.putExtra("username", "tim_cook")
            setResult(Activity.RESULT_OK,intent)
            finish()
        }else if (sender is WriteTweetFragment){
            val newTweet = sender.getTextTweet()
            Toast.makeText(this, "New Tweet added", Toast.LENGTH_SHORT).show()
            val intent = Intent(this,CreateBlockActivity::class.java)
            intent.putExtra("block_type", "WriteTweet")
            intent.putExtra("newTweet",newTweet)
            setResult(Activity.RESULT_OK,intent)
            finish()
        }
    }

    fun showLoginPopup() {
        val inflater: LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val view = inflater.inflate(R.layout.twitter_login_popup, twitter_activity)
        val popupWindow = PopupWindow(
            view, // Custom view to show in popup window
            LinearLayout.LayoutParams.WRAP_CONTENT, // Width of popup window
            LinearLayout.LayoutParams.WRAP_CONTENT // Window height
        )
        // Settings window popup
        popupWindow.setBackgroundDrawable(ColorDrawable(Color.WHITE)) //Background color
        popupWindow.isFocusable = true //for click EditText

        val buttonLogin = view.findViewById<Button>(R.id.save_button)
        val fieldUsername = view.findViewById<EditText>(R.id.editUsername)
        val fieldPassword = view.findViewById<EditText>(R.id.editPassword)
        buttonLogin.setOnClickListener{
            if(fieldUsername.equals("") && fieldPassword.equals(""))
                Toast.makeText(this, "Fields empties", Toast.LENGTH_SHORT).show()
            else if(fieldUsername.equals("") || fieldPassword.equals(""))
                Toast.makeText(this, "Username or password field empty", Toast.LENGTH_SHORT).show()
            else {
                //controllo credenziali
                popupWindow.dismiss()
            }
        }
        popupWindow.setOnDismissListener {
            Toast.makeText(applicationContext,"Login Done",Toast.LENGTH_SHORT).show()
        }

        // Finally, show the popup window on app
        TransitionManager.beginDelayedTransition(twitter_activity)
        popupWindow.showAtLocation(
            twitter_activity, // Location to display popup window
            Gravity.CENTER_HORIZONTAL, // Exact position of layout to display popup
            0, // X offset
            0 // Y offset
        )

    }
}
