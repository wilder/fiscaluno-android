package com.fiscaluno.contracts

import android.content.Intent
import com.fiscaluno.model.Student


interface LoginContract {
    interface View {
        fun successfulLogin(student: Student)
        fun loginCancelled()
        fun loginError(message: String)
    }

    interface Presenter {
        fun bindView(view: View)
        fun doLogin(student: Student, token: String)
        fun prepareForLogin()
        fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent)
    }
}
