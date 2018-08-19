package com.fiscaluno.contracts

import com.fiscaluno.model.Course
import com.fiscaluno.model.Institution

/**
 * Created by Wilder on 14/08/17.
 */
interface MainContract {
    interface View {
        fun showTopInstitutions(institutions: List<Institution>?)
        fun showTopCourses(courses: List<Course>)
    }
    interface Presenter {
        fun bindView(view: MainContract.View)
        fun loadTopInstitutions()
        fun loadTopCourses()
    }
}