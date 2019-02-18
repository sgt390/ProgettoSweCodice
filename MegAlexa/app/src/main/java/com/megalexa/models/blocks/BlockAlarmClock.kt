package com.megalexa.models.blocks
import java.util.Calendar
class BlockAlarmClock(private val minutes: Int, private val hours: Int,
                      private val day: Int, private val mounth: Int, private val year: Int, month: Int) :Calendar{

    private var time = Calendar("$hours:$minutes",)
    init{
        require(minutes >= 0 && minutes < 60){println("")}
        require(hours >= 0 && hours < 60){println("")}
        require(day > 0){println("")}
        require(mounth > 0){println("")}
    }
    //ritorna l'orario in cui suona la sveglia
    fun getInformation() : String{
        return "$year/$mounth/$day $hours:$minutes"
    }
    fun alarmTime(): {
        return time
    }

}