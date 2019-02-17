package com.fiscaluno.contracts

import android.content.Context
import android.content.Intent
import com.facebook.AccessToken
import com.fiscaluno.model.Student


interface LoginContract {
    interface View {
        fun successfulLogin(student: Student)
        fun loginCancelled()
        fun loginError(message: String)
    }

    interface Presenter {
        fun loginWithFacebook(student: Student, token: AccessToken)
        fun prepareFacebookLogin()
        fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent)
        fun bindView(view: View, context: Context)
        fun loginWithEmail(email: String, pass: String)
    }
}
