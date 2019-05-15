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
import com.megalexa.models.blocks.BlockCalendar
import com.megalexa.util.service.BlockCalendarService
import junit.framework.Assert.assertEquals
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class BlockCalendarConversionTest:ConversionTest {
    val token : String = "ya29adAGI_KoNUp0frFpNsw"
    val refresh : String = "1/ewqewq"

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        Assert.assertEquals("com.megalexa", appContext.packageName)
    }

    @Test
    override fun conversionFromJSontoObject() {
        val expected = BlockCalendar(token,refresh)
        val json = BlockCalendarService.convertToJSON(expected)
        val block = BlockCalendarService.convertFromJSON(json)
        Assert.assertEquals(expected.getToken(),block.getToken())
    }

    @Test
    override fun conversionFromObjectToJSon() {
        val block=BlockCalendar("account","refresh")
        val json=BlockCalendarService.convertToJSON(block)
        assertEquals(json.toString(),"{\"blockType\":\"Calendar\",\"config\":{\"credentials\":{\"installed\":{\"auth_provider_x509_cert_url\":\"https:\\/\\/www.googleapis.com\\/oauth2\\/v1\\/certs\",\"auth_uri\":\"https:\\/\\/accounts.google.com\\/o\\/oauth2\\/auth\",\"client_id\":\"974570768081-dj6r57189hnove2avo5pp0kpfl8864bk.apps.googleusercontent.com\",\"client_secret\":\"RxrbY8tUFTIHUZ9gijZlFSp0\",\"project_id\":\"megalexa-1556132707047\",\"redirect_uris\":[\"urn:ietf:wg:oauth:2.0:oob\",\"http:\\/\\/localhost\"],\"token_uri\":\"https:\\/\\/oauth2.googleapis.com\\/token\"}},\"token\":{\"access_token\":\"account\",\"expires_in\":100000,\"refresh_token\":\"refresh\",\"scope\":\"https:\\/\\/www.googleapis.com\\/auth\\/calendar.readonly\",\"token_type\":\"Bearer\"}}}")
    }
}