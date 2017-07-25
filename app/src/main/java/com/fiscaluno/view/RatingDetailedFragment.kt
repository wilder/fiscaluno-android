package com.fiscaluno.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.fiscaluno.R
import com.fiscaluno.model.GeneralReview

class RatingDetailedFragment : Fragment() {

    // TODO: Rename and change types of parameters
    private var generalReview: GeneralReview? = null
    private var institutionName: TextView? = null
    private var institutionImage: ImageView? = null
    private var reviewsList: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            generalReview = arguments.get(RatingDetailedFragment.REVIEW_PARAM) as GeneralReview?
        }
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
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val REVIEW_PARAM = "review"


        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: GeneralReview): RatingDetailedFragment {
            val fragment = RatingDetailedFragment()
            val args = Bundle()
            args.putParcelable(REVIEW_PARAM, param1)
            fragment.arguments = args
            return fragment
        }

    }
}
