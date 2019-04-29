package com.megalexa.util

import com.megalexa.models.MegAlexa
import com.megalexa.viewModel.MegAlexaViewModelFactory
import com.megalexa.viewModel.WorkflowViewModel
import com.megalexa.viewModel.WorkflowViewModelFactory

object InjectorUtils {

    fun provideMegAlexaViewModelFactory():MegAlexaViewModelFactory {
        val app = MegAlexa.getInstance()
        return MegAlexaViewModelFactory(app)
    }

    fun provideWorkflowViewModelFactory(wName:String) :WorkflowViewModelFactory{
        val app= MegAlexa.getInstance()
        return WorkflowViewModelFactory(app,wName)
    }

}