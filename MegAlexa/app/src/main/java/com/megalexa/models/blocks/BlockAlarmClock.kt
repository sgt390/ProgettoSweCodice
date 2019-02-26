/*
 * File: BlockAlarmClock.kt
 * Version: 0.1
 * Date: 2019/02/17
 * Author: Andrea Deidda
 *
 * License:
 * History: registro delle modifiche
 * Andrea Deidda || 2019/02/17 || Creating file and header
 * Andrea Deidda || 2019/02/20 || Add methods
 * Andrea Deidda || 2019/02/26 || Add setDate() method
 *
 */
package com.megalexa.models.blocks
import android.media.Ringtone
import java.time.Month
import java.util.*
import kotlin.math.min

class BlockAlarmClock(private var minutes: Int, private var hours: Int,
                      private var month: Int, private var day:Int,
                      private var year:Int, private var sound: Ringtone) : Block{
    val calendar = Calendar.getInstance()
    //set calendar
    init {
        //controls will do in fragment file later
        calendar.set(Calendar.HOUR_OF_DAY, hours)
        calendar.set(Calendar.MINUTE, minutes)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.YEAR,year)
        calendar.set(Calendar.MONTH,month)
        calendar.set(Calendar.DAY_OF_MONTH,day)
    }

    //Set time method
    fun setTime(hour: Int,minute: Int) {
        minutes = minute
        hours = hour
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
    }
    //Set date method
    fun setDate(y: Int, m: Int, d:Int) {
        year = y
        month = m
        day = d
        calendar.set(Calendar.YEAR,year)
        calendar.set(Calendar.MONTH,month)
        calendar.set(Calendar.DAY_OF_MONTH,day)
    }
    //Return informations about this block
    override fun getInformation() : String{
        return "AlarmClock block creates a personal alarm clock"
    }
    //Return alarm clock set time
    fun alarmTime(): String {
        return "Alarm set at $hours:$minutes"
    }
}