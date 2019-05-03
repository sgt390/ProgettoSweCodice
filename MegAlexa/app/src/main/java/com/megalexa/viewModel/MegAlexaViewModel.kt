package com.megalexa.viewModel


import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.util.Log
import com.megalexa.models.MegAlexa
import com.megalexa.models.workflow.Workflow
import com.megalexa.util.service.MegAlexaService
import com.megalexa.util.service.UserService
import com.megalexa.util.service.WorkflowService
import org.jetbrains.anko.doAsync


class MegAlexaViewModel(private val app: MegAlexa): ViewModel() {

    private var wNames = MutableLiveData<ArrayList<String>>()

    /**
     * returns the adapter that must be assigned  to activities (with workflow names)
     */
    fun getLiveWorkflowNames(): LiveData<ArrayList<String>> {
        if(wNames.value == null)
            loadWorkflow()
        return wNames
    }

    /**this functions sets the correct instance for the MegAlexa object
     * calls the API with a GET function using Service classes
     */
    fun loadAppContext() {
        val jsonObject=MegAlexaService.getOperation(listOf(Pair("userID",app.getUser().getID())))
        app.setInstance(MegAlexaService.convertFromJSON(jsonObject))
        refreshWorkflow()
    }

    fun setUser(user: com.amazon.identity.auth.device.api.authorization.User) {
        app.setUser(com.megalexa.models.User(user.userId, user.userName, user.userEmail))
    }


    fun isUserPresent(paramID:String):Boolean {
        if(app.getUser().getID()==paramID)
            return true
        return false
    }

    /**
     * loads the adapter that must be assigned  to activities (with workflow names)
     */
    private fun loadWorkflow() {
        val myHandler = android.os.Handler()

        myHandler.postDelayed({
            val workflowNames= app.getWorkflowList()
            val names= ArrayList<String>()
            for(item in workflowNames) {
                names.add(item.getName())
            }
            wNames.value= names
        }, 5000)
    }

    private fun refreshWorkflow() {
        val names =app.getWorkflowNames()
        wNames.postValue(names)
    }

    fun saveUser() {
        val user= app.getUser()
        val json= UserService.convertToJSON(user)
        doAsync {UserService.postOperation(json)}
    }

    fun removeWorkflow(position :Int) {
        val list= app.getWorkflowList()
        doAsync {WorkflowService.deleteOperation(listOf(Pair("userID",app.getUser().getID()), Pair("workflowName", app.getWorkflowNames().get(position))))}
        list.removeAt(position)
        refreshWorkflow()
    }

    fun cancelPreviousIntent(){
        refreshWorkflow()
    }

}

class MegAlexaViewModelFactory(private val app: MegAlexa):
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MegAlexaViewModel(app) as T
    }
}
