package com.megalexa.ui.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.NumberPicker
import com.megalexa.R
import com.megalexa.ui.activities.CreateBlockActivity
import com.megalexa.util.service.BlockReadEmailService
import org.jetbrains.anko.doAsync

class MailFragment: Fragment(){

    private var cardinality = 5

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.mail_fragment_layout,container,false)
        val filter = view.findViewById<Button>(R.id.button_filter)
        val button= view.findViewById<Button>(R.id.confirmItemBotton_mail)

        filter.setOnClickListener{
            val builder = AlertDialog.Builder(ContextThemeWrapper(context,R.style.Theme_AppCompat_Dialog))
            val pickerLayout =inflater.inflate(R.layout.simple_number_picker,null)
            val picker=pickerLayout.findViewById<NumberPicker>(R.id.number_picker)
            picker.minValue=1
            picker.maxValue=5

            val confirmFilter = {
                    _: DialogInterface, _:Int -> cardinality = picker.value
            }

            val cancelFilter = {
                    _: DialogInterface, _:Int ->
            }

            with(builder) {
                setView(pickerLayout)
                setPositiveButton("Confirm",confirmFilter)
                setNegativeButton("Cancel",cancelFilter)
            }
            builder.show()
        }
        button.setOnClickListener {
            //doAsync {  BlockReadEmailService.getCredentials(BlockReadEmailService.HTTP_TRANSPORT)}
                val activity= activity as CreateBlockActivity
                activity.onFragmentClick(this)
        }

        return view
    }

    fun getCardinality() = cardinality

}