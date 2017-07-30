package com.fiscaluno.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager

import com.fiscaluno.R
import com.fiscaluno.contracts.InstitutionDetailContract
import com.fiscaluno.model.DetailedReview
import com.fiscaluno.model.GeneralReview
import com.fiscaluno.presenter.InstitutionDetailPresenter
import com.fiscaluno.view.adapter.DetailedReviewAdapter
import com.fiscaluno.view.adapter.InstitutionDetailGeneralReviewsAdapter
import kotlinx.android.synthetic.main.activity_institution_detail.*
import java.util.ArrayList

class InstitutionDetailActivity : AppCompatActivity(), InstitutionDetailContract.View {

    private var presenter: InstitutionDetailContract.Presenter? = null
    private var detailedReviewAdapter: DetailedReviewAdapter? = null
    private var generalReviewAdapter: InstitutionDetailGeneralReviewsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_institution_detail)
        //intent.getParcelableExtra<Institution>("institution")
        presenter = InstitutionDetailPresenter()
        presenter?.loadInstitution("0")
    }

    override fun setupDetailedReviewsList(reviews: ArrayList<DetailedReview>){
        detailedReviewAdapter = DetailedReviewAdapter(reviews)
        detailedReviewsRv.adapter = detailedReviewAdapter
        detailedReviewsRv.layoutManager = LinearLayoutManager(baseContext)
    }

    override fun setupGeneralReviewsList(reviews: ArrayList<GeneralReview>){
        generalReviewAdapter = InstitutionDetailGeneralReviewsAdapter(reviews)
        generalReviewsRv.adapter = detailedReviewAdapter
        generalReviewsRv.layoutManager = LinearLayoutManager(baseContext)
    }

}
