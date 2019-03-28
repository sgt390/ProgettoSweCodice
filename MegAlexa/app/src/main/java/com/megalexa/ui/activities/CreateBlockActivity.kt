package com.megalexa.ui.activities


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.*
import com.megalexa.R
import com.megalexa.adapters.view.ListArrayAdapter
import com.megalexa.ui.fragments.RssFragment
import com.megalexa.ui.fragments.TextToSpeechFragment
import com.megalexa.util.view.FragmentClickListener
import com.megalexa.viewModel.ViewModelMain
import kotlinx.android.synthetic.main.activity_create_block.*


class CreateBlockActivity: AppCompatActivity(), View.OnClickListener, FragmentClickListener {

    private lateinit var listView: ListView
    companion object {
        private var viewModel = ViewModelMain()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_block)

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
                    //TODO() FILTER FRAGMENT
                }
                3-> {
                    //TODO() PIN FRAGMENT
                }
                4-> {
                    //TODO() EMAIL FRAGMENT
                }
                5-> {
                    //TODO() NEWS FRAGMENT
                }
                6-> {
                    //TODO() SPORT FRAGMENT
                }
            }

        }

    }

    override fun onFragmentClick(sender: Fragment) {


        if(sender is RssFragment){
            val url = sender.getUrl()
            val intent = Intent(this,CreateWorkflowActivity::class.java)
            intent.putExtra("block_type", "FeedRss")
            intent.putExtra("feedRss",url)
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

        }
    }

    override fun onClick(view: View) {


    when(view.id){
        R.id.button_cancel_block -> {
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
            Pair(list[2], R.drawable.ic_filter),
            Pair(list[3], R.drawable.ic_lock),
            Pair(list[4], R.drawable.ic_email),
            Pair(list[5], R.drawable.ic_news),
            Pair(list[6], R.drawable.ic_sport)
        )

    }

    private fun getTitlesList(): List<String> {

        return listOf("FeedRSS","Text Block","Filter","PIN","Read Email","News","Sport News")

    }

    override fun onBackPressed() {
        if(!listView.isEnabled)
            listView.isEnabled=true
            super.onBackPressed()
    }
}
