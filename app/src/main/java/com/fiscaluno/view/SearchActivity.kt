package com.fiscaluno.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.fiscaluno.App
import com.fiscaluno.R
import com.fiscaluno.contracts.SearchContract
import com.fiscaluno.model.*
import com.fiscaluno.presenter.SearchPresenter
import com.fiscaluno.view.adapter.CoursesAdapter
import com.fiscaluno.view.adapter.InstitutionsAdapter
import kotlinx.android.synthetic.main.activity_search_result.*

class SearchActivity : AppCompatActivity(), SearchContract.View {

    private lateinit var presenter: SearchContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)

        val kodein = (application as App).kodein
        presenter = SearchPresenter(kodein)
        presenter.bindView(this)

        val searchFilter = intent.extras.getParcelable<SearchFilter>("searchFilterExtra")
        searchFilter?.searchableEntity?.search(presenter, searchFilter)

        searchResultTv.text = searchFilter.searchableEntity?.getValue()

    }

    override fun displayCourses(searchResult: List<Course>?) {
        val topInstitutionsAdapter = CoursesAdapter(ArrayList(searchResult), this)
        searchResultRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        searchResultRv.adapter = topInstitutionsAdapter
    }

    override fun displayInstitutions(searchResult: List<Institution>?) {
        val topInstitutionsAdapter = InstitutionsAdapter(ArrayList(searchResult), this)
        searchResultRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        searchResultRv.adapter = topInstitutionsAdapter
    }

}
