/*
 *
 *  File name. BlockCalendarCoversionTest.kt
 *  Version: 1.0.0
 *  Date: 2019-04-16
 *  Author: Ludovico Brocca
 *  License:
 *  History:
 *  Author            || Date            || Description
 *  Ludovico Brocca   || 2019-04-16      || File created
 *  Gian Marco Bratzu || 2019-04-17     || Verifying code
 *
 */
package com.megalexa.service.conversion

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.util.Log
import com.megalexa.models.blocks.BlockCalendar
import com.megalexa.util.service.BlockCalendarService
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class BlockCalendarConversionTest:ConversionTest {
val token : String = "ya29adAGI_KoNUp0frFpNsw"
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        Assert.assertEquals("com.megalexa", appContext.packageName)
    }

    @Test
    override fun conversionFromJSontoObject() {
        val expected = BlockCalendar(token)
        val json = BlockCalendarService.convertToJSON(expected)
        val block = BlockCalendarService.convertFromJSON(json)
        Assert.assertEquals(expected.getToken(),block.getToken() )

    }

    @Test
    override fun conversionFromObjectToJSon() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}