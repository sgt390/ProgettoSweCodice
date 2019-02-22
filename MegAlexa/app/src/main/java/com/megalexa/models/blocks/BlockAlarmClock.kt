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
import android.content.Context
import java.util.Calendar
import android.media.Ringtone
import android.media.RingtoneManager
class BlockAlarmClock(private var minutes: Int, private var hours: Int,
                      private var sound: Ringtone) : Block{
    val calendar = Calendar.getInstance()
    //set calendar
    init {
        calendar.set(Calendar.HOUR_OF_DAY, hours)
        calendar.set(Calendar.MINUTE, minutes)
    }

    //Set method
    fun setAlarm(hour: Int,minute: Int){
        minutes = minute
        hours = hour
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
    }
    //Return informations about this block
    override fun getInformation() : String{
        return "Alarm Clock block information:" +
                "\tTime ${calendar.get(Calendar.HOUR_OF_DAY)}:${calendar.get(Calendar.MINUTE)}"
                //+ "\tRingtone ${sound.getTitle(getText())}"
    }
    //Return alarm clock set time
    fun alarmTime(): String {
        return "Alarm set at $hours:$minutes"
    }
}