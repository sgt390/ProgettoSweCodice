/*
 *
 *  File name: CreateBlockActivity.kt
 *  Version: 1.0.0
 *  Date: 2019-03-01
 *  Author: Ludovico Brocca
 *  License:
 *  History:
 *  Author            || Date            || Description
 *  Ludovico Brocca   || 2019-03-01      || File created
 *  Matteo Depascale  || 2019-03-10      || Verifying code
 *
 */

package com.megalexa.ui.activities


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ListView
import android.widget.Toast
import com.megalexa.R
import com.megalexa.ui.adapters.ListArrayAdapter
import com.megalexa.ui.fragments.*
import com.megalexa.util.view.FragmentClickListener
import kotlinx.android.synthetic.main.activity_create_block.*


class CreateBlockActivity: AppCompatActivity(), View.OnClickListener, FragmentClickListener {

    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_block)
        supportActionBar?.hide()

        listView = findViewById(R.id.view_blocks)
        listView.isScrollContainer=true
        val blockList = getBlockList()


        listView.adapter = ListArrayAdapter(this, blockList)

        button_cancel_block.setOnClickListener(this)

        var fragment: Fragment

        listView.setOnItemClickListener{

             _ ,_ ,position, _ ->

            when(position){
                0-> {
                    fragment = RssFragment()
                    val transaction = supportFragmentManager.beginTransaction()
                    listView.isEnabled=false
                    transaction.replace(R.id.fragment_container, fragment).addToBackStack("").commit()
                }
                1-> {
                    fragment = TextToSpeechFragment()
                    listView.isEnabled=false
                    val transaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.fragment_container, fragment).addToBackStack("").commit()
                }
                2-> {
                    fragment = PinFragment()
                    listView.isEnabled=false
                    val transaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.fragment_container, fragment).addToBackStack("").commit()
                }
                3-> {
                    val intent = Intent(this@CreateBlockActivity, GoogleActivity::class.java)
                    startActivityForResult(intent, 1)
                }
                4-> {
                    fragment = NewsFragment()
                    val transaction = supportFragmentManager.beginTransaction()
                    listView.isEnabled=false
                    transaction.replace(R.id.fragment_container, fragment).addToBackStack("").commit()
                }
                5 -> {
                    fragment = SportFragment()
                    val transaction = supportFragmentManager.beginTransaction()
                    listView.isEnabled=false
                    transaction.replace(R.id.fragment_container, fragment).addToBackStack("").commit()
                }
                6 -> {
                    val intent = Intent(this@CreateBlockActivity, TwitterActivity::class.java)
                    startActivityForResult(intent, 1)
                }
                7 -> {
                    fragment = BorsaFragment()
                    val transaction = supportFragmentManager.beginTransaction()
                    listView.isEnabled=false
                    transaction.replace(R.id.fragment_container, fragment).addToBackStack("").commit()
                }
                8 -> {
                    fragment = WeatherFragment()
                    val transaction = supportFragmentManager.beginTransaction()
                    listView.isEnabled=false
                    transaction.replace(R.id.fragment_container, fragment).addToBackStack("").commit()
                }
                9 -> {
                    fragment = CryptoFragment()
                    val transaction = supportFragmentManager.beginTransaction()
                    listView.isEnabled = false
                    transaction.replace(R.id.fragment_container, fragment).addToBackStack("").commit()
                }
                10-> {
                    fragment = ListFragment()
                    val transaction = supportFragmentManager.beginTransaction()
                    listView.isEnabled=false
                    transaction.replace(R.id.fragment_container, fragment).addToBackStack("").commit()
                }
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK) {
            val blockType = data!!.extras!!.getString("block_type")
            when(blockType){
                "TwitterUserTL" -> {
                    val anotherUser = data.extras!!.get("username")
                    val intent = Intent(this, CreateWorkflowActivity::class.java)
                    intent.putExtra("cardinality",data.extras?.get("cardinality").toString())
                    intent.putExtra("block_type", "TwitterUserTL")
                    intent.putExtra("screenName" ,anotherUser?.toString())
                    setResult(Activity.RESULT_OK,intent)
                    finish()
                }
                "TwitterHashtag" -> {
                    val hashtag = data.extras!!.get("screenName")
                    val intent = Intent(this, CreateWorkflowActivity::class.java)
                    intent.putExtra("cardinality",data.extras?.get("cardinality").toString())
                    intent.putExtra("block_type", "TwitterHashtag")
                    intent.putExtra("hashtag" ,hashtag?.toString())
                    setResult(Activity.RESULT_OK,intent)
                    finish()
                }
                "TwitterHomeTL" -> {
                    //val myUsername = data.extras!!.get("username")
                    val intent = Intent(this, CreateWorkflowActivity::class.java)
                    intent.putExtra("cardinality",data.extras?.get("cardinality").toString())
                    intent.putExtra("block_type", "TwitterHomeTL")
                    //intent.putExtra("username" ,myUsername?.toString())
                    setResult(Activity.RESULT_OK,intent)
                    finish()
                }
                "WriteTweet" -> {
                val intent = Intent(this, CreateWorkflowActivity::class.java)
                intent.putExtra("cardinality",data.extras?.get("cardinality").toString())
                intent.putExtra("block_type", "WriteTwitter")
                setResult(Activity.RESULT_OK,intent)
                finish()
                 }
                "Email" -> {
                    val intent = Intent(this, CreateWorkflowActivity::class.java)
                    val token = data.extras!!.get("token")
                    val refreshToken = data.extras!!.get("refreshToken")
                    intent.putExtra("cardinality",data.extras?.get("cardinality").toString())
                    intent.putExtra("block_type", "Email")
                    intent.putExtra("access_token",token.toString())
                    intent.putExtra("refresh_token",refreshToken.toString())
                    setResult(Activity.RESULT_OK,intent)
                    finish()
                }
                "Calendar" -> {
                    val intent = Intent(this, CreateWorkflowActivity::class.java)
                    val token = data.extras!!.get("token")
                    val refreshToken = data.extras!!.get("refreshToken")
                    intent.putExtra("cardinality",data.extras?.get("cardinality").toString())
                    intent.putExtra("block_type", "Calendar")
                    intent.putExtra("access_token",token?.toString())
                    intent.putExtra("refresh_token",refreshToken.toString())
                    setResult(Activity.RESULT_OK,intent)
                    finish()
                }

            }
        }
    }

    override fun onFragmentClick(sender: Fragment) {

        if(sender is RssFragment){
            val url = sender.getUrl()
            val intent = Intent(this,CreateWorkflowActivity::class.java)
            intent.putExtra("cardinality",sender.getCardinality())
            intent.putExtra("block_type", "FeedRss")
            intent.putExtra("FeedUrl",url)
            setResult(Activity.RESULT_OK, intent)
            listView.isEnabled=true
            finish()

        }else if(sender is TextToSpeechFragment){
            val text= sender.getText()
            val intent = Intent(this,CreateWorkflowActivity::class.java)
            intent.putExtra("block_type", "Text to speech")
            intent.putExtra("text",text)
            setResult(Activity.RESULT_OK,intent)
            listView.isEnabled=true
            finish()

        }else if(sender is PinFragment){
            val pin= sender.getPin()
            val intent = Intent(this,CreateWorkflowActivity::class.java)
            intent.putExtra("block_type", "Pin")
            intent.putExtra("pin",pin)
            setResult(Activity.RESULT_OK,intent)
            listView.isEnabled=true
            finish()

        }else if(sender is NewsFragment){
            val news= sender.getUrl()
            val intent = Intent(this,CreateWorkflowActivity::class.java)
            intent.putExtra("cardinality",sender.getCardinality())
            intent.putExtra("block_type", "News")
            intent.putExtra("news",news)
            setResult(Activity.RESULT_OK,intent)
            listView.isEnabled=true
            finish()

        }else if(sender is SportFragment){
            val sport = sender.getUrl()
            val intent = Intent(this,CreateWorkflowActivity::class.java)
            intent.putExtra("cardinality",sender.getCardinality())
            intent.putExtra("block_type", "Sport")
            intent.putExtra("sport",sport)
            setResult(Activity.RESULT_OK,intent)
            listView.isEnabled=true
            finish()

        }else if(sender is CryptoFragment){
            val crypto = sender.getUrl()
            Toast.makeText(this,crypto,Toast.LENGTH_SHORT).show()
            val intent = Intent(this,CreateWorkflowActivity::class.java)
            intent.putExtra("cardinality",sender.getCardinality())
            intent.putExtra("block_type", "Crypto")
            intent.putExtra("crypto",crypto)
            setResult(Activity.RESULT_OK,intent)
            listView.isEnabled=true
            finish()

        }else if(sender is BorsaFragment){
            val borsa = sender.getUrl()
            Toast.makeText(this,borsa,Toast.LENGTH_SHORT).show()
            val intent = Intent(this,CreateWorkflowActivity::class.java)
            intent.putExtra("cardinality",sender.getCardinality())
            intent.putExtra("block_type", "Borsa")
            intent.putExtra("borsa",borsa)
            setResult(Activity.RESULT_OK,intent)
            listView.isEnabled=true
            finish()

        }else if(sender is TwitterFragment){
            val hashtag = sender.getTwit()
            val access_token_key = sender.getAccess_token_()
            val access_token_secret = sender.getAccess_token_twit_()
            val consumer_key = sender.getConsumer_api()
            val consumer_secret = sender.getConsumer_api_secret()
            Toast.makeText(this,hashtag,Toast.LENGTH_SHORT).show()
            val intent = Intent(this,CreateWorkflowActivity::class.java)
            intent.putExtra("cardinality",sender.getCardinality())
            intent.putExtra("block_type", "Twitter")
            intent.putExtra("access_token_key",access_token_key)
            intent.putExtra("access_token_secret",access_token_secret)
            intent.putExtra("consumer_key",consumer_key)
            intent.putExtra("consumer_secret",consumer_secret)
            intent.putExtra("screenName",hashtag)
            setResult(Activity.RESULT_OK,intent)
            listView.isEnabled=true
            finish()

        }else if(sender is WeatherFragment) {

            val city= sender.getCity()
            val intent= Intent(this, CreateWorkflowActivity::class.java)
            intent.putExtra("block_type","Weather")
            intent.putExtra("city",city)
            setResult(Activity.RESULT_OK,intent)
            listView.isEnabled=true
            finish()

        } else if(sender is ListFragment) {
            val intent = Intent(this, CreateWorkflowActivity::class.java)
            intent.putExtra("block_type", "List")
            setResult(Activity.RESULT_OK, intent)
            listView.isEnabled = true
            finish()
        }

    }

    override fun onClick(view: View) {

        when(view.id){
            R.id.button_cancel_block -> {
                setResult(Activity.RESULT_CANCELED)
                this.finish()
            }
        }

    }

    private fun getBlockList(): List<Pair<String, Int>> {

        val list = getTitlesList()
        //more pairs to be added
        return listOf(
            Pair(list[0], R.drawable.ic_feed_rss),
            Pair(list[1], R.drawable.ic_text),
            Pair(list[2], R.drawable.ic_lock),
            Pair(list[3], R.drawable.ic_google),
            Pair(list[4], R.drawable.ic_news),
            Pair(list[5], R.drawable.ic_sport),
            Pair(list[6], R.drawable.ic_twitter),
            Pair(list[7], R.drawable.ic_stock),
            Pair(list[8], R.drawable.ic_weather),
            Pair(list[9], R.drawable.ic_crypto),
            Pair(list[10], R.drawable.ic_list)
        )

    }

    private fun getTitlesList(): List<String> {

        return listOf(
            this.resources.getString(R.string.ListFeedRss),
            this.resources.getString(R.string.ListTextBlock),
            this.resources.getString(R.string.ListPin),
            this.resources.getString(R.string.ListGoogle),
            this.resources.getString(R.string.ListNews),
            this.resources.getString(R.string.ListSportNews),
            this.resources.getString(R.string.ListTwitter),
            this.resources.getString(R.string.ListStockNews),
            this.resources.getString(R.string.ListWeather),
            this.resources.getString(R.string.ListCryptoNews),
            this.resources.getString(R.string.ListList)

        )

    }

    override fun onBackPressed() {
        if(!listView.isEnabled)
            listView.isEnabled=true
            super.onBackPressed()
    }
}
