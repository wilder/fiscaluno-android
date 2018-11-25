package com.fiscaluno.presenter

import android.util.Log
import com.fiscaluno.contracts.SearchContract
import com.fiscaluno.model.Course
import com.fiscaluno.model.Institution
import com.fiscaluno.model.SearchFilter
import com.fiscaluno.network.FiscalunoApi
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import org.kodein.di.Kodein
import org.kodein.di.generic.instance

class SearchPresenter(val kodein: Kodein) : SearchContract.Presenter {
    private lateinit var view: SearchContract.View
    private var db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val INSTITUTIONS = "Institutions"
    private val COURSES = "courses"

    override fun bindView(view: SearchContract.View) {
        this.view = view
    }

    override fun searchCourse(searchFilter: SearchFilter, page: Int, pageSize: Int) {
        val collection = db.collection(COURSES)

        if (searchFilter.searchableEntity != null) {
            collection.whereEqualTo("name", searchFilter.searchableEntity?.getValue())
        }

        collection
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val courses = task.result.toObjects(Course::class.java)
                    Log.d("topCourses", courses.toString())
                    view.displayCourses(courses)
                } else {
                    Log.w("topCourses", "Error getting documents.", task.getException())
                }
            }

    }

    override fun searchInstitution(searchFilter: SearchFilter, page: Int, pageSize: Int) {
        val collection = db.collection(INSTITUTIONS)

        if (searchFilter.searchableEntity != null) {
            collection.whereEqualTo("name", searchFilter.searchableEntity?.getValue())
        }

        collection
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val institutions = task.result.toObjects(Institution::class.java)
                    Log.d("topCourses", institutions.toString())
                    view.displayInstitutions(institutions)
                } else {
                    Log.w("topCourses", "Error getting documents.", task.getException())
                }
            }

    }


}