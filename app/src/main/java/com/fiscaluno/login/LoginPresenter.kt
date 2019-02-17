package com.fiscaluno.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import com.facebook.*
import com.facebook.login.LoginResult
import com.fiscaluno.contracts.LoginContract
import com.fiscaluno.model.Student
import org.json.JSONObject
import com.facebook.login.LoginManager
import com.facebook.FacebookCallback
import com.fiscaluno.contracts.StudentRepository
import com.fiscaluno.repository.FirebaseStudentRepository
import com.fiscaluno.repository.UserRepository
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import org.kodein.di.Kodein
import org.kodein.di.generic.instance


class LoginPresenter(val kodein: Kodein) : LoginContract.Presenter {

    lateinit var view: LoginContract.View
    private lateinit var context: Context
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val studentRepository: StudentRepository by kodein.instance()

    //TODO: Inject
    lateinit var firebaseStudentRepository: FirebaseStudentRepository
    private var callbackManager: CallbackManager? = null
    private val facebookReadPermissions = arrayListOf("email", "public_profile", "user_hometown")

    override fun bindView(view: LoginContract.View, context: Context) {
        this.view = view
        this.firebaseStudentRepository = FirebaseStudentRepository()
        this.context = context
    }

    override fun loginWithEmail(email: String, pass: String) {
        auth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(context as Activity) { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser!!
                        val student: Student = studentRepository.getUserById(user.uid)!!
                        view.successfulLogin(student)
                    } else {
                        // If sign in fails, display a message to the user.
                        view.loginError("Unable to login")
                    }
                }
    }

    override fun loginWithFacebook(student: Student, token: AccessToken) {
        handleFacebookAccessToken(AccessToken.getCurrentAccessToken(), student)
    }

    private fun handleFacebookAccessToken(token: AccessToken, student: Student) {

        val credential = FacebookAuthProvider.getCredential(token.token)
        auth.signInWithCredential(credential)
                .addOnCompleteListener(context as Activity) { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        student.id = user!!.uid
                        studentRepository.saveUser(student)
                        view.successfulLogin(student)
                    } else {
                        // If sign in fails, display a message to the user.
                        view.loginError("Unable to login")
                    }
                }
    }

    override fun prepareFacebookLogin() {
        callbackManager = CallbackManager.Factory.create()
        val callback = LoginCallback()
        LoginManager.getInstance().logInWithReadPermissions(view as Fragment, facebookReadPermissions)
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

                loginWithFacebook(student, loginResult.accessToken)

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
            val hometown: JSONObject? = (jsonObject.opt("hometown") as JSONObject?)
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