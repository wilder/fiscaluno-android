package com.fiscaluno.repository

import android.util.Log
import com.fiscaluno.contracts.UserRepositoryContract
import com.fiscaluno.model.Student
import com.google.firebase.firestore.FirebaseFirestore

class UserRepository() : UserRepositoryContract {

    private var db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val STUDENTS = "Students"

    override fun saveUser(student: Student) {
        db.collection(STUDENTS)
                .add(student)
                .addOnSuccessListener {
                    Log.d(STUDENTS, "Student successfully written!")
                }
                .addOnFailureListener {
                    e ->
                    run {
                        Log.w(STUDENTS, "Error writing student", e)
                    }
                }
    }

}