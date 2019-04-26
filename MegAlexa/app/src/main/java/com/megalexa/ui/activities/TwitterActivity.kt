package com.megalexa.ui.activities

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.widget.LinearLayout
import android.widget.Toast
import com.megalexa.R
import com.megalexa.ui.fragments.TwitterAnotherUserFragment
import com.megalexa.ui.fragments.TwitterFragment
import com.megalexa.ui.fragments.TwitterReadTimeLineUser
import com.megalexa.ui.fragments.WriteTweetFragment
import com.megalexa.util.view.FragmentClickListener

class TwitterActivity : AppCompatActivity(), FragmentClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_twitter)

        val user = findViewById<LinearLayout>(R.id.userTwitter)
        val anotherUser = findViewById<LinearLayout>(R.id.homeTwitter)
        val hashtag = findViewById<LinearLayout>(R.id.SearchHashtag)
        val writeTweet = findViewById<LinearLayout>(R.id.writeTweet)

        user.setOnClickListener {
            val fragment = TwitterReadTimeLineUser()
            val transaction = supportFragmentManager.beginTransaction()
            //transaction.replace(R.id.container_fragment, fragment).addToBackStack("").commit()
        }
        anotherUser.setOnClickListener {
            val fragment = TwitterAnotherUserFragment()
            val transaction = supportFragmentManager.beginTransaction()
            //transaction.replace(R.id.container_fragment, fragment).addToBackStack("").commit()
        }
        hashtag.setOnClickListener {
            val fragment = TwitterFragment()
            val transaction = supportFragmentManager.beginTransaction()
            //transaction.replace(R.id.container_fragment, fragment).addToBackStack("").commit()
        }
        writeTweet.setOnClickListener {
            val fragment = WriteTweetFragment()
            val transaction = supportFragmentManager.beginTransaction()
            //transaction.replace(R.id.container_fragment, fragment).addToBackStack("").commit()
        }
    }

    override fun onFragmentClick(sender: Fragment) {
        if(sender is TwitterAnotherUserFragment){
            val anotherUser = sender.getUserName()
            Toast.makeText(this,anotherUser,Toast.LENGTH_SHORT).show()
            val intent = Intent(this,CreateBlockActivity::class.java)
            intent.putExtra("cardinality",sender.getCardinality())
            intent.putExtra("block_type", "TwitterAnotherUser")
            intent.putExtra("anotherUser",anotherUser)
            setResult(Activity.RESULT_OK,intent)
            finish()
        }else if (sender is TwitterFragment){
            val hashtag = sender.getTwit()
            Toast.makeText(this,hashtag,Toast.LENGTH_SHORT).show()
            val intent = Intent(this,CreateBlockActivity::class.java)
            intent.putExtra("cardinality",sender.getCardinality())
            intent.putExtra("block_type", "Twitter")
            intent.putExtra("screenName",hashtag)
            setResult(Activity.RESULT_OK,intent)
            finish()
        }else if (sender is TwitterReadTimeLineUser){
            Toast.makeText(this, "Twitter read user timeline", Toast.LENGTH_SHORT).show()
            val intent = Intent(this,CreateBlockActivity::class.java)
            intent.putExtra("cardinality", sender.getCardinality())
            intent.putExtra("block_type", "TwitterReadUserTimeline")
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
}
