package com.fiscaluno.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fiscaluno.R
import com.fiscaluno.model.GeneralReview

class RatingGeneralFragment : Fragment() {

    private var reviewParam: GeneralReview? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            reviewParam = arguments.get(REVIEW_PARAM) as GeneralReview?
            Log.d("test", reviewParam.toString()+"oi")
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_rating_general, container, false)
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
