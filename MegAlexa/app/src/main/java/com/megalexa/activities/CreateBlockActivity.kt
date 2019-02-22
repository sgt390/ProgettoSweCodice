package com.megalexa.activities


import android.app.Dialog
import android.app.Fragment
import android.app.Fragment.instantiate
import android.os.Bundle

import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.Window
import android.widget.*
import com.megalexa.R
import com.megalexa.adapters.view.ListArrayAdapter
import kotlinx.android.synthetic.main.activity_create_block.*
import com.megalexa.fragments.RssFragment
import java.util.zip.Inflater


class CreateBlockActivity: AppCompatActivity(), View.OnClickListener {

    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_block)

        listView = findViewById(R.id.view_blocks)
        val blockList = getBlockList()


        listView.adapter = ListArrayAdapter(this, blockList)


        button_cancel_block.setOnClickListener(this)




        listView.setOnItemClickListener{
                _,_,position, _
            ->

            if(position==0)
                showRSSDialog()

            if(position==1)
                Toast.makeText(this,"position $position", Toast.LENGTH_SHORT).show()

            if(position==2)
                Toast.makeText(this,"position $position", Toast.LENGTH_SHORT).show()

            if(position==3)
                Toast.makeText(this,"position $position", Toast.LENGTH_SHORT).show()


        }



        //RSS listener



        /*listView.getChildAt(0).setOnClickListener {
            // Get the text fragment instance
            val textFragment = RssFragment()

            // Get the support fragment manager instance
            val manager = supportFragmentManager

            // Begin the fragment transition using support fragment manager
            val transaction = manager.beginTransaction()

            // Replace the fragment on container
            transaction.replace(R.id.fragment_container, textFragment)
            transaction.addToBackStack(null)

            // Finishing the transition
            transaction.commit()
        }*/


    }

    override fun onClick(view: View) {


    TODO()





    }



    private fun getBlockList(): List<Pair<String, Int>> {

        val list = getTitlesList()
        //more pairs to be added
        return listOf(
            Pair(list[0], R.drawable.ic_mr_button_connecting_30_light),
            Pair(list[1], R.drawable.ic_mr_button_connecting_30_light),
            Pair(list[2], R.drawable.ic_mr_button_connecting_30_light),
            Pair(list[3], R.drawable.ic_mr_button_connecting_30_light)
        )

    }


    private fun getTitlesList(): List<String> {

        return listOf("Add FeedRSS", "Add Alarm Clock", "Add Text Block", "Add Filter")

    }


    private fun showRSSDialog(){

        var view= Dialog(this)

        view.setContentView(R.layout.rss_fragment_layout)
       /* val textView= view.findViewById<TextView>(R.id.Url)
        val editText= view.findViewById<EditText>(R.id.insert_URL)
        val button=view.findViewById<Button>(R.id.confirm_button)

        button.setOnClickListener { view.dismiss() }
*/
        view.show()
    }

}
