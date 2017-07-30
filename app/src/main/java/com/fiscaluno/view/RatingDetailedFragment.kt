package com.fiscaluno.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.fiscaluno.R
import com.fiscaluno.contracts.DetailedReviewContract
import com.fiscaluno.model.DetailedReview
import com.fiscaluno.model.GeneralReview
import com.fiscaluno.model.Institution
import com.fiscaluno.presenter.DetailedReviewPresenter
import com.fiscaluno.view.adapter.DetailedReviewAdapter
import com.fiscaluno.view.adapter.InstitutionListAdapter
import java.util.ArrayList

class RatingDetailedFragment : Fragment(), DetailedReviewContract.View {

    private var generalReview: GeneralReview? = null
    private var institutionName: TextView? = null
    private var institutionImage: ImageView? = null
    private var reviewsList: RecyclerView? = null
    private var adapter: DetailedReviewAdapter? = null
    private var presenter: DetailedReviewContract.Presenter? = null

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        institutionName?.setText(generalReview?.institution?.name)
        institutionImage?.setImageDrawable(resources.getDrawable(R.mipmap.ic_launcher)) //TODO: get image
        presenter?.loadReviews()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            generalReview = arguments.get(RatingDetailedFragment.REVIEW_PARAM) as GeneralReview?
        }
        presenter = DetailedReviewPresenter()
        presenter?.bindView(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_rating_detailed, container, false)

        institutionName = view.findViewById(R.id.institution_name_tv_gr) as TextView
        institutionImage = view.findViewById(R.id.institution_small_image_gr) as ImageView
        reviewsList = view.findViewById(R.id.detailedReviewsRv) as RecyclerView

        return view
    }

    companion object {
        private val REVIEW_PARAM = "review"

        fun newInstance(param1: GeneralReview): RatingDetailedFragment {
            val fragment = RatingDetailedFragment()
            val args = Bundle()
            args.putParcelable(REVIEW_PARAM, param1)
            fragment.arguments = args
            return fragment
        }
    }

    override fun setupDetailedReviewsList(review: ArrayList<DetailedReview>) {
        setupList(review)
    }

    private fun setupList(reviews: ArrayList<DetailedReview>){
        adapter = DetailedReviewAdapter(reviews, true)
        reviewsList?.adapter = adapter
        reviewsList?.layoutManager = LinearLayoutManager(context)
    }
}
