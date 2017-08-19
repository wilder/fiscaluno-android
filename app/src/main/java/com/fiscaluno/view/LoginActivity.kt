package com.fiscaluno.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.fiscaluno.R
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.FacebookCallback
import com.facebook.login.widget.LoginButton
import android.view.ViewGroup
import android.view.LayoutInflater
import com.facebook.CallbackManager
import android.content.Intent
import android.util.Log
import com.facebook.FacebookSdk
import com.facebook.login.LoginManager
import kotlinx.android.synthetic.main.activity_login.*
import java.util.*
import com.facebook.GraphResponse
import org.json.JSONObject
import com.facebook.GraphRequest




class LoginActivity : AppCompatActivity() {

    lateinit var callbackManager: CallbackManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FacebookSdk.sdkInitialize(getApplicationContext())

        setContentView(R.layout.activity_login)

        val loginButton = findViewById(R.id.login_button) as LoginButton
        loginButton.setReadPermissions("email", "public_profile", "user_about_me", "user_birthday", "user_education_history", "user_work_history")

        callbackManager = CallbackManager.Factory.create();

        loginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                val request = GraphRequest.newMeRequest(
                        loginResult.accessToken
                ) { jsonObject, response ->
                    Log.d("JsonObject", jsonObject.toString())
                }
                val parameters = Bundle()
                parameters.putString("fields", "id,email,name,link,education,birthday,about,gender,hometown,work")
                request.parameters = parameters
                request.executeAsync()
            }

            override fun onCancel() {
                // App code
            }

            override fun onError(exception: FacebookException) {
                // App code
            }
        })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

}
