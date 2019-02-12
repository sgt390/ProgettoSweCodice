package com.megalexa

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.amazon.identity.auth.device.AuthError
import com.amazon.identity.auth.device.api.Listener
import com.amazon.identity.auth.device.api.authorization.AuthorizationManager
import android.content.Intent

class AuthenticatedUser : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authenticated_user)
        var logoutButton : View = findViewById(R.id.logout_button)
        logoutButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v : View){
                AuthorizationManager.signOut(applicationContext, object : Listener<Void, AuthError> {

                    override fun onSuccess(response : Void ){
                        startActivity(Intent(this@AuthenticatedUser, MainActivity::class.java))
                    }

                    override fun onError(authError: AuthError){

                    }
                })
            }
        })
    }
}
