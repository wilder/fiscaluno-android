package com.fiscaluno.contracts

import com.fiscaluno.model.Course
import com.fiscaluno.model.Institution
import com.fiscaluno.model.RateableEntity
import com.fiscaluno.model.SearchFilter

interface SearchContract {

    interface Presenter {
        fun searchInstitution(searchFilter: SearchFilter, page: Int = 0, pageSize: Int = 5)
        fun searchCourse(searchFilter: SearchFilter, page: Int = 0, pageSize: Int = 5)
        fun bindView(view: View)
    }

    interface View {
        fun displayInstitutions(searchResult: List<Institution>?)
        fun displayCourses(searchResult: List<Course>?)
    }

}