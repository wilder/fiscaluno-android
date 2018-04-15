package com.fiscaluno.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.facebook.*
import com.facebook.login.LoginResult
import com.fiscaluno.contracts.LoginContract
import com.fiscaluno.model.Student
import org.json.JSONObject
import com.facebook.login.LoginManager
import android.app.Activity
import com.facebook.FacebookCallback



class LoginPresenter : LoginContract.Presenter {

    lateinit var view: LoginContract.View
    private var callbackManager: CallbackManager? = null
    private val facebookReadPermissions = arrayListOf("email", "public_profile", "user_hometown")

    override fun bindView(view: LoginContract.View) {
        this.view = view
    }

    override fun doLogin() {
        val callback = LoginCallback()
        LoginManager.getInstance().logInWithReadPermissions(view as Activity, facebookReadPermissions)
        LoginManager.getInstance().registerCallback(callbackManager, callback)

    }


    override fun prepareForLogin() {
        callbackManager = CallbackManager.Factory.create()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (FacebookSdk.isFacebookRequestCode(requestCode)) {
            callbackManager?.onActivityResult(requestCode, resultCode, data)
        }
    }

    private inner class LoginCallback : FacebookCallback<LoginResult> {
        override fun onSuccess(loginResult: LoginResult) {
            val request = GraphRequest.newMeRequest(loginResult.accessToken) { jsonObject, request ->

                Log.d("JsonObject", jsonObject.toString())

                val student = getUserFromResponse(jsonObject)
                view.successfulLogin(student)

            }

            val parameters = Bundle()
            parameters.putString("fields", "id,email,name,link,education,birthday,about,gender,hometown,work")
            request.parameters = parameters
            request.executeAsync()
        }

        private fun getUserFromResponse(jsonObject: JSONObject): Student {
            val student = Student()
            student.fbId = jsonObject.get("id") as String?
            student.birthday = jsonObject.get("birthday") as String?
            student.city = (jsonObject.get("hometown") as JSONObject).get("name") as String? //TODO
            student.email = jsonObject.get("email") as String?
            student.gender = jsonObject.get("gender") as String?
            student.name = jsonObject.get("name") as String?
            student.nacionality = (jsonObject.get("hometown") as JSONObject).get("name") as String? //TODO
            student.fbInstitutionName = ""
            return student
        }

        override fun onCancel() {
            view.loginCancelled()
        }

        override fun onError(exception: FacebookException) {
            view.loginError(exception.message.orEmpty())
        }
    }
}