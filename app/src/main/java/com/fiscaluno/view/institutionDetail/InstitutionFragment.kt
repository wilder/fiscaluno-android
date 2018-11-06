package com.fiscaluno.view.institutionDetail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fiscaluno.App

import com.fiscaluno.R
import com.fiscaluno.contracts.ReviewsContract
import com.fiscaluno.model.DetailedReview
import com.fiscaluno.model.GeneralReview
import com.fiscaluno.model.Institution
import com.fiscaluno.presenter.ReviewsPresenter
import com.fiscaluno.rating.detailedReview.DetailedReviewAdapter
import com.fiscaluno.view.adapter.InstitutionDetailGeneralReviewsAdapter
import kotlinx.android.synthetic.main.fragment_institution.*

private const val INSTITUTION_PARAM = "param1"

class InstitutionFragment : Fragment(), ReviewsContract.View {

    private lateinit var institution: Institution
    private lateinit var presenter: ReviewsContract.Presenter
    private var detailedReviewAdapter: DetailedReviewAdapter? = null
    private var generalReviewAdapter: InstitutionDetailGeneralReviewsAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_institution, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            institution = it.getParcelable(INSTITUTION_PARAM)
        }

        presenter = ReviewsPresenter((activity?.application as App).kodein)
        presenter.bindView(this)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ratingAverageRb.rating = institution.averageRating

        // TODO: coroutines
        presenter.loadDetailedReviews(institution.id!!)
        presenter.loadGeneralReviews(institution.id!!)
    }

    override fun setupGeneralReviews(generalReviews: List<GeneralReview>?) {
        generalReviewAdapter = InstitutionDetailGeneralReviewsAdapter(generalReviews!!)
        generalReviewsRv.adapter = generalReviewAdapter
        generalReviewsRv.layoutManager = LinearLayoutManager(context)
    }

    override fun setupDetailedReviews(detailedReviews: List<DetailedReview>?) {
        detailedReviewAdapter = DetailedReviewAdapter(detailedReviews!!, false)
        detailedReviewsRv.adapter = detailedReviewAdapter
        detailedReviewsRv.layoutManager = LinearLayoutManager(context)
    }

    companion object {
        @JvmStatic
        fun newInstance(institution: Institution) =
                InstitutionFragment().apply {
                    arguments = Bundle().apply {
                        putParcelable(INSTITUTION_PARAM, institution)
                    }
                }
    }
}
