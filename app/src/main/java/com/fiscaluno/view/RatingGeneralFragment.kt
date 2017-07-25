package com.fiscaluno.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.fiscaluno.R
import com.fiscaluno.model.GeneralReview

class RatingGeneralFragment : Fragment() {

    private var reviewParam: GeneralReview? = null
    private var institutionImage: ImageView? = null
    private var institutionNameTv: TextView? = null
    private var reviewTitleTv: EditText? = null
    private var prosTv: EditText? = null
    private var consTv: EditText? = null
    private var suggestionsTv: EditText? = null
    private var ratingBar: RatingBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            reviewParam = arguments.get(REVIEW_PARAM) as GeneralReview?
        }
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        institutionImage?.setImageDrawable(resources.getDrawable(R.drawable.ic_location_city_black_24dp)) //TODO: Get institution image
        institutionNameTv?.text = reviewParam?.institution?.name
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_rating_general, container, false)

        institutionImage = view.findViewById(R.id.institution_small_image_gn) as ImageView
        institutionNameTv = view.findViewById(R.id.institution_name_tv_gn) as TextView
        reviewTitleTv = view.findViewById(R.id.review_title_et_gn) as EditText
        prosTv = view.findViewById(R.id.pros_et_gn) as EditText
        consTv = view.findViewById(R.id.cons_et_gn) as EditText
        suggestionsTv = view.findViewById(R.id.suggestions_et_gn) as EditText
        ratingBar = view.findViewById(R.id.rating_stars_gn) as RatingBar

        return view
    }

    companion object {
        private val REVIEW_PARAM = "review"
        fun newInstance(review: GeneralReview): RatingGeneralFragment {
            val fragment = RatingGeneralFragment()
            val args = Bundle()
            args.putParcelable(REVIEW_PARAM, review)
            fragment.arguments = args
            return fragment
        }
    }
}
