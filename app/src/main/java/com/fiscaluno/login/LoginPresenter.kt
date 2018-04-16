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
import com.fiscaluno.repository.UserRepository


class LoginPresenter : LoginContract.Presenter {

    lateinit var view: LoginContract.View
    //TODO: Inject
    lateinit var userRepository: UserRepository
    private var callbackManager: CallbackManager? = null
    private val facebookReadPermissions = arrayListOf("email", "public_profile", "user_hometown")

    override fun bindView(view: LoginContract.View) {
        this.view = view
        this.userRepository = UserRepository()
    }

    override fun doLogin() {


    }


    override fun prepareForLogin() {
        callbackManager = CallbackManager.Factory.create()
        val callback = LoginCallback()
        LoginManager.getInstance().logInWithReadPermissions(view as Activity, facebookReadPermissions)
        LoginManager.getInstance().registerCallback(callbackManager, callback)
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
                userRepository.saveUser(student)
                view.successfulLogin(student)

            }

            val parameters = Bundle()
            parameters.putString("fields", "id,email,name,link,education,birthday,about,gender,hometown,work")
            request.parameters = parameters
            request.executeAsync()
        }

        private fun getUserFromResponse(jsonObject: JSONObject): Student {
            val student = Student()
            student.fbId = jsonObject.optString("id")
            student.birthday = jsonObject.optString("birthday")
            student.city = (jsonObject.opt("hometown") as JSONObject).optString("name")
            student.email = jsonObject.optString("email")
            student.gender = jsonObject.optString("gender")
            student.name = jsonObject.optString("name")
            student.nacionality = (jsonObject.opt("hometown") as JSONObject).optString("name")
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