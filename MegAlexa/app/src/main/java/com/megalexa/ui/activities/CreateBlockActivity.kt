package com.megalexa.ui.activities


import android.app.Activity
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.*
import com.megalexa.R
import com.megalexa.ui.adapters.ListArrayAdapter
import com.megalexa.ui.fragments.*
import com.megalexa.util.InjectorUtils
import com.megalexa.util.view.FragmentClickListener
import com.megalexa.viewModel.MegAlexaViewModel
import kotlinx.android.synthetic.main.activity_create_block.*


class CreateBlockActivity: AppCompatActivity(), View.OnClickListener, FragmentClickListener {

    private lateinit var listView: ListView
    companion object {
        private lateinit var viewModel :MegAlexaViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_block)
        supportActionBar?.hide()
        val factory= InjectorUtils.provideMegAlexaViewModelFactory()
        viewModel = ViewModelProviders.of(this,factory).get(MegAlexaViewModel::class.java)

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
                    //TODO() EMAIL FRAGMENT
                }
                4-> {
                    fragment = NewsFragment()
                    val transaction = supportFragmentManager.beginTransaction()
                    listView.isEnabled=false
                    transaction.replace(R.id.fragment_container, fragment).addToBackStack("").commit()
                }
                5-> {
                    fragment = SportFragment()
                    val transaction = supportFragmentManager.beginTransaction()
                    listView.isEnabled=false
                    transaction.replace(R.id.fragment_container, fragment).addToBackStack("").commit()
                }

                6-> {
                    fragment = CryptoFragment()
                    val transaction = supportFragmentManager.beginTransaction()
                    listView.isEnabled=false
                    transaction.replace(R.id.fragment_container, fragment).addToBackStack("").commit()
                }

                7-> {
                    fragment = BorsaFragment()
                    val transaction = supportFragmentManager.beginTransaction()
                    listView.isEnabled=false
                    transaction.replace(R.id.fragment_container, fragment).addToBackStack("").commit()
                }
                8-> {
                    fragment = TwitterFragment()
                    val transaction = supportFragmentManager.beginTransaction()
                    listView.isEnabled=false
                    transaction.replace(R.id.fragment_container, fragment).addToBackStack("").commit()
                }
                9-> {
                    fragment = WeatherFragment()
                    val transaction = supportFragmentManager.beginTransaction()
                    listView.isEnabled=false
                    transaction.replace(R.id.fragment_container, fragment).addToBackStack("").commit()
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
            Pair(list[3], R.drawable.ic_email),
            Pair(list[4], R.drawable.ic_news),
            Pair(list[5], R.drawable.ic_sport),
            Pair(list[6], R.drawable.ic_news),
            Pair(list[7], R.drawable.ic_news),
            Pair(list[8], R.drawable.ic_text),
            Pair(list[9], R.drawable.ic_text)
            )

    }

    private fun getTitlesList(): List<String> {

        return listOf("FeedRSS","Text Block","PIN",
            "Read Email","News","Sport News","Crypto News","Stock News","Read Tweet","Weather")

    }


    override fun onBackPressed() {
        if(!listView.isEnabled)
            listView.isEnabled=true
            super.onBackPressed()
    }
}
