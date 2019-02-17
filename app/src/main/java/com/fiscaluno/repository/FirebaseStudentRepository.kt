package com.fiscaluno.repository

import android.util.Log
import com.fiscaluno.contracts.StudentRepository
import com.fiscaluno.model.Student
import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.FirebaseFirestore

class FirebaseStudentRepository : StudentRepository {

    private var db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val STUDENTS = "Students"

    override fun saveUser(student: Student) {
        db.collection(STUDENTS)
                .document(student.id!!)
                .set(student)
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

    override fun getUserById(id: String): Student? {

        val result = Tasks.await(db.collection(STUDENTS)
                .document(id)
                .get())

        return result.toObject(Student::class.java)

    }

}