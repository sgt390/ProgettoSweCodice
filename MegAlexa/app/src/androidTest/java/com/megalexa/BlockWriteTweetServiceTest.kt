package com.megalexa

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.megalexa.util.service.BlockTwitterService

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
class BlockWriteTweetServiceTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.megalexa", appContext.packageName)
    }

    @Test
    fun validToJSON() {
        TODO()
    }

    @Test
    fun validFromJSON() {
        TODO()
    }
}