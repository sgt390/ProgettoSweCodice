package com.megalexa.models.blocks

import java.text.DateFormat

class BlockAlarmClock(private val time: DateFormat) : Block{

    override fun getInformation():String  {
        TODO("not implemented")
    }

    fun alarmTime():DateFormat{
        return time
    }

}