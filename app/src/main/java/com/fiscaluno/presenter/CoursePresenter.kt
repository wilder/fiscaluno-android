package com.fiscaluno.presenter

import android.util.Log
import com.fiscaluno.contracts.CourseContract
import com.fiscaluno.model.Course
import com.fiscaluno.network.FiscalunoApi
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.kodein.di.Kodein
import org.kodein.di.generic.instance

class CoursePresenter(val kodein: Kodein) : CourseContract.Presenter {
    private lateinit var view: CourseContract.View
    private var db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val COURSES = "courses"

    override fun bindView(view: CourseContract.View) {
        this.view = view
    }

    override fun findInstitutionCourses(institutionId: Int) {
        db.collection(COURSES)
                .whereEqualTo("institutionId", institutionId)
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val courses = task.result.toObjects(Course::class.java)
                        Log.d("Courses", courses.toString())
                        view.showInstitutionCourses(courses)
                    } else {
                        Log.w("Courses", "Error getting documents.", task.getException())
                    }
                }
    }


}
