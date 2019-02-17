package com.fiscaluno.login

import android.content.Context
import android.content.Intent
import com.fiscaluno.contracts.SignInContract
import com.fiscaluno.model.Student
import com.fiscaluno.repository.FirebaseStudentRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class SignInPresenter : SignInContract.Presenter {

    lateinit var view: SignInContract.View
    lateinit var auth: FirebaseAuth
    val studentRepository = FirebaseStudentRepository()


    override fun bindView(view: SignInContract.View) {
        this.view = view
        this.auth = FirebaseAuth.getInstance()
    }

    override fun signInWithEmail(name: String, lastname: String, email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        val user = auth.currentUser
                        val student = buildStudent(user!!, name, lastname)
                        studentRepository.saveUser(student)
                        view.success(student)
                    } else {
                        view.siginError(it.exception?.localizedMessage!!)
                    }
                }
    }

    private fun buildStudent(user: FirebaseUser, name: String, lastname: String): Student {
        val student = Student()
        student.email = user.email
        student.id = user.uid
        student.name = "$name $lastname"
        return student
    }

    override fun signInWithGoogle() {

    }

    override fun signInWithFacebook() {

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {

    }
}