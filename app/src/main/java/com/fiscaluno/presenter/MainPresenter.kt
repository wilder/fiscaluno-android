package com.fiscaluno.presenter

import com.fiscaluno.contracts.MainContract
import com.fiscaluno.model.Course
import com.fiscaluno.model.Institution

/**
 * Created by Wilder on 14/08/17.
 */
class MainPresenter : MainContract.Presenter{

    lateinit var view: MainContract.View

    override fun bindView(view: MainContract.View) {
        this.view = view
    }

    /**
     * Loads the top rated Institutions
     */
    override fun loadTopInstitutions() {
        //TODO: Load top Institutions
        val topInstitutions: ArrayList<Institution> = ArrayList()
        for (i in 0..5) {
            val institution = Institution()
            institution.name = "test $i"
            institution.averageRating = i * 0.9f
            institution.id = i.toString()
            institution.reviewdBy = 132 * i
            topInstitutions.add(institution)
        }
        view.showTopInstitutions(topInstitutions)
    }

    override fun loadTopCourses() {
        //TODO: Load top courses
        val topCourses: ArrayList<Course> = ArrayList()
        for (i in 0..5) {
            val institution = Institution()
            institution.name = "Faculdade $i"
            val course = Course()
            course.name = "test $i"
            course.averageRating = i * 0.9f
            course.id = i.toString()
            course.reviewdBy = 132 * i
            course.institution = institution
            topCourses.add(course)
        }
        view.showTopCourses(topCourses)
    }

}