package com.megalexa.util

import com.megalexa.models.MegAlexa
import com.megalexa.viewModel.MegAlexaViewModelFactory

object InjectorUtils {

    fun provideMegAlexaViewModelFactory():MegAlexaViewModelFactory {

        val app = MegAlexa.getInstance()
        return MegAlexaViewModelFactory(app)
    }
}