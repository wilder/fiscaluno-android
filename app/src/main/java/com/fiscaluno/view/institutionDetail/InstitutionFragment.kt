package com.fiscaluno.view.institutionDetail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fiscaluno.App

import com.fiscaluno.R
import com.fiscaluno.contracts.InstitutionDetailContract
import com.fiscaluno.model.DetailedReview
import com.fiscaluno.model.GeneralReview
import com.fiscaluno.model.Institution
import com.fiscaluno.presenter.InstitutionDetailPresenter
import com.fiscaluno.rating.detailedReview.DetailedReviewAdapter
import com.fiscaluno.view.adapter.InstitutionDetailGeneralReviewsAdapter
import kotlinx.android.synthetic.main.fragment_institution.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val INSTITUTION_PARAM = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [InstitutionFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [InstitutionFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class InstitutionFragment : Fragment(), InstitutionDetailContract.View {

    private lateinit var institution: Institution
    private lateinit var presenter: InstitutionDetailContract.Presenter
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

        presenter = InstitutionDetailPresenter((activity?.application as App).kodein)
        presenter.bindView(this)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ratingAverageRb.rating = institution.averageRating

        // TODO: coroutines
        presenter.loadDetailedReviews(institution.id)
        presenter.loadGeneralReviews(institution.id)
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