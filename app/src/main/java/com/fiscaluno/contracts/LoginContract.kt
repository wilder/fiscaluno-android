package com.fiscaluno.login

import android.content.Intent


interface LoginContract {
    interface View {
        fun successfulLogin()
    }

    interface Presenter {
        fun bindView(view: LoginContract.View)
        fun doLogin(institutionId: String)
        fun prepareForLogin()
        fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent)
    }
}
