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
import com.fiscaluno.data.FiscalunoApi
import com.fiscaluno.repository.UserRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.kodein.di.Kodein
import org.kodein.di.generic.instance


class LoginPresenter(val kodein: Kodein) : LoginContract.Presenter {

    lateinit var view: LoginContract.View
    private val api: FiscalunoApi by kodein.instance()
    //TODO: Inject
    lateinit var userRepository: UserRepository
    private var callbackManager: CallbackManager? = null
    private val facebookReadPermissions = arrayListOf("email", "public_profile", "user_hometown")

    override fun bindView(view: LoginContract.View) {
        this.view = view
        this.userRepository = UserRepository()
    }

    override fun doLogin(student: Student) {
        api.authenticate(AuthenticationBody(student.fbId!!))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.code() == 401) {
                        //view.badRequest("Login expirado")
                        Log.e("LoginPresenter", "unable to authenticate user - 401")
                    } else if (it.code() == 500) {
                        //view.badRequest("Não foi possível buscar as aulas.\nTente novamente mais tarde.")
                        Log.e("LoginPresenter", "unable to authenticate user - 500")
                    } else {
                        userRepository.saveUser(student)
                        view.successfulLogin(student)
                    }
                },{
                    Log.e("LoginPresenter", "unable to authenticate user - ${it.message}")
                    //view.badRequest("Não foi possível buscar as aulas.\nTente novamente mais tarde.")
                })
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

                doLogin(student)

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
            val hometown: JSONObject? = (jsonObject.opt("hometown") as JSONObject)
            student.city = hometown?.optString("name")
            student.email = jsonObject.optString("email")
            student.gender = jsonObject.optString("gender")
            student.name = jsonObject.optString("name")
            student.nacionality = hometown?.optString("name")
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