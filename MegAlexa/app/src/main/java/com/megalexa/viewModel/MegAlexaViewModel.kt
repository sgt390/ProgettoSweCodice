package com.megalexa.viewModel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.megalexa.models.MegAlexa

class MegAlexaViewModel(private val app: MegAlexa): ViewModel() {


    /**this functions sets the correct instance for the MegAlexa object
     * fetches from SharedInstances and decides if it' necessary to call api gateway
     * to read the correct instance
     */
    fun loadAppContext(userID: String){
      //todo()
    }


    /**
     * saves information in SharedPreferences when the app closes
     */
    fun saveAppContext(){
       //todo()
    }
}

class MegAlexaViewModelFactory(private val app: MegAlexa):
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MegAlexaViewModel(app) as T
    }
}
