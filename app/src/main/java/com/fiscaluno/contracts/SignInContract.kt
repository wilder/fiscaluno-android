package com.fiscaluno.contracts

import android.content.Intent
import com.fiscaluno.model.Student


interface SignInContract {
    interface View {
        fun success(student: Student)
        fun siginCancelled()
        fun siginError(message: String)
    }

    interface Presenter {
        fun bindView(view: View)
        fun signInWithGoogle()
        fun signInWithFacebook()
        fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent)
        fun signInWithEmail(name: String, lastname: String, email: String, password: String)
    }
}
