package com.fiscaluno.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.fiscaluno.R
import com.fiscaluno.model.GeneralReview

class RatingGeneralFragment : Fragment() {

    private var reviewParam: GeneralReview? = null
    private var institutionImage: ImageView? = null
    private var institutionNameTv: TextView? = null
    private var reviewTitleEt: EditText? = null
    private var prosTv: EditText? = null
    private var consTv: EditText? = null
    private var suggestionsEt: EditText? = null
    private var ratingBar: RatingBar? = null
    private var addButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            reviewParam = arguments.get(REVIEW_PARAM) as GeneralReview?
        }
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        institutionImage?.setImageDrawable(resources.getDrawable(R.mipmap.ic_launcher)) //TODO: Get institution image
        institutionNameTv?.text = reviewParam?.institution?.name
        addButton?.setOnClickListener {
            //TODO: Validate Values
            reviewParam?.rate = ratingBar?.rating
            reviewParam?.description = reviewTitleEt?.text.toString()
            reviewParam?.cons = consTv?.text.toString()
            reviewParam?.pros = prosTv?.text.toString()
            reviewParam?.suggestion = suggestionsEt?.text.toString()

            val adapter = (activity as RatingActivity).mPagerAdapter
            val pager = (activity as RatingActivity).mViewPager
            adapter?.add(RatingDetailedFragment.newInstance(reviewParam!!))
            pager?.setCurrentItem(pager.currentItem + 1, true)
            Toast.makeText(this.context, reviewParam?.toString(), Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_rating_general, container, false)

        institutionImage = view.findViewById(R.id.institution_small_image_gn) as ImageView
        institutionNameTv = view.findViewById(R.id.institution_name_tv_gn) as TextView
        reviewTitleEt = view.findViewById(R.id.review_title_et_gn) as EditText
        prosTv = view.findViewById(R.id.pros_et_gn) as EditText
        consTv = view.findViewById(R.id.cons_et_gn) as EditText
        suggestionsEt = view.findViewById(R.id.suggestions_et_gn) as EditText
        ratingBar = view.findViewById(R.id.rating_stars_gn) as RatingBar
        addButton = view.findViewById(R.id.add_btn_gn) as Button

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
