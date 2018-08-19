package com.fiscaluno.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager

import com.fiscaluno.R
import com.fiscaluno.contracts.InstitutionDetailContract
import com.fiscaluno.model.DetailedReview
import com.fiscaluno.model.GeneralReview
import com.fiscaluno.model.Institution
import com.fiscaluno.presenter.InstitutionDetailPresenter
import com.fiscaluno.rating.detailedReview.DetailedReviewAdapter
import com.fiscaluno.view.adapter.InstitutionDetailGeneralReviewsAdapter
import kotlinx.android.synthetic.main.activity_institution_detail.*
import kotlin.collections.ArrayList

class InstitutionDetailActivity : AppCompatActivity(), InstitutionDetailContract.View {

    private var presenter: InstitutionDetailContract.Presenter? = null
    private var detailedReviewAdapter: DetailedReviewAdapter? = null
    private var generalReviewAdapter: InstitutionDetailGeneralReviewsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_institution_detail)
        val institution = intent.getParcelableExtra<Institution>("institution")
        presenter = InstitutionDetailPresenter()
        presenter?.bindView(this)
        //TODO: pass whole institution?
        presenter?.loadInstitution(institution.id.toString())
    }

    override fun setupInstitutionDetails(institution: Institution) {
        institution_institution_name.text = institution.name
        rating_average.rating = institution.averageRating!!
        average_tv.text = institution.averageRating!!.toString()
        val reviewdBy = institution.ratedByCount
        reviews_count_tv.text = "Avaliado por $reviewdBy alunos"
        setupDetailedReviewsList(ArrayList(institution.detailedReviews))
        setupGeneralReviewsList(ArrayList(institution.generalReviews))
    }

    private fun setupDetailedReviewsList(reviews: ArrayList<DetailedReview>){
        detailedReviewAdapter = DetailedReviewAdapter(reviews, false)
        detailedReviewsRv.adapter = detailedReviewAdapter
        detailedReviewsRv.layoutManager = LinearLayoutManager(baseContext)
    }

    private fun setupGeneralReviewsList(reviews: ArrayList<GeneralReview>){
        generalReviewAdapter = InstitutionDetailGeneralReviewsAdapter(reviews)
        generalReviewsRv.adapter = generalReviewAdapter
        generalReviewsRv.layoutManager = LinearLayoutManager(baseContext)
    }

}
