package com.megalexa.models.blocks

import com.megalexa.adapters.connectors.Connector
import java.text.DateFormat

class BlockAlarmClock(val time: DateFormat) : Block{

    override fun getInformation() {
        TODO("not implemented")
    }

    fun alarmTime():DateFormat{
        return time
    }

}