package com.megalexa.ui.fragments

import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.megalexa.R
import com.megalexa.ui.activities.CreateBlockActivity
import com.megalexa.util.service.BlockCalendarService
import org.jetbrains.anko.doAsync

class MailFragment: Fragment(){

    private var text =  ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.mail_fragment_layout,container,false)

        val button= view.findViewById<Button>(R.id.confirmItemBotton_mail)


        button.setOnClickListener {

            Toast.makeText(context, "text is empty", Toast.LENGTH_SHORT).show()
            doAsync {  BlockCalendarService.getCredentials(BlockCalendarService.HTTP_TRANSPORT)}
//            if(editText.text.toString() == "") {
//                Toast.makeText(context, "text is empty", Toast.LENGTH_SHORT).show()
//            }
//            else{
//                text= editText.text.toString()
                val activity= activity as CreateBlockActivity
                activity.onFragmentClick(this)
//            }

        }


        return view
    }

    fun getText(): String{
        return text
    }

}