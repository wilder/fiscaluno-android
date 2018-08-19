package com.fiscaluno.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.fiscaluno.R
import com.fiscaluno.contracts.SearchContract
import com.fiscaluno.model.RateableEntity

class SearchActivity : AppCompatActivity(), SearchContract.View {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)
    }

    override fun displaySerchResult(searchResult: List<RateableEntity>) {
//        val topInstitutionsAdapter = TopInstitutionsAdapter(ArrayList(institutions), this)
//        topInstitutionsRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
//        topInstitutionsRv.adapter = topInstitutionsAdapter
    }

}
