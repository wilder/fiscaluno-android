package com.fiscaluno.contracts

import com.fiscaluno.model.Course

interface CourseContract {

    interface Presenter {
        fun bindView(view: View)
        fun findInstitutionCourses(institutionId: Int)
    }

    interface View {
        fun showInstitutionCourses(courses: List<Course>?)
    }

}