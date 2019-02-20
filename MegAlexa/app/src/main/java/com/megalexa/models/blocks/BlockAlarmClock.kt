/*
 * File: BlockAlarmClock.kt
 * Version: 0.1
 * Date: 2019/02/17
 * Author: Andrea Deidda
 *
 * License:
 * History: registro delle modifiche
 * Andrea Deidda || 2019/02/17 || Creazione file ed intestazione
 * Andrea Deidda || 2019/02/20 || Sviluppo metodi
 *
 */
package com.megalexa.models.blocks
class BlockAlarmClock(private val minutes: Int, private val hours: Int,
                      private val day: Int, private val mounth: Int,
                      private val year: Int) : Block{
    init{
        require(minutes >= 0 && minutes < 60){println("")}
        require(hours >= 0 && hours < 60){println("")}
        require(day > 0){println("")}
        require(mounth > 0){println("")}
    }
    //ritorna l'orario in cui suona la sveglia
    override fun getInformation() : String{
        return "Alarm Clock block's information:" +
                "\tDate: $year/$mounth/$day" +
                "\tTime: $hours:$minutes"
    }
    fun alarmTime(): Int{
        return time
    }
}