package com.megalexa.models.blocks

import java.text.DateFormat

class BlockAlarmClock(private val time: DateFormat) : Block{
    init{
        require(time. > 0){...}
    }
    //ritorna l'orario in cui suona la sveglia
    override fun getInformation() : Block

    fun alarmTime(): DateFormat{
        return time
    }

}