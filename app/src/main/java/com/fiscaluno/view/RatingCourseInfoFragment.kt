package com.fiscaluno.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.fiscaluno.R
import com.fiscaluno.model.GeneralReview
import com.fiscaluno.model.Institution

class RatingCourseInfoFragment : Fragment() {

    // TODO: Rename and change types of parameters
    private var instParam: Institution? = null
    private var institutionName: TextView? = null
    private var institutionImage: ImageView? = null
    private var courseEt: EditText? = null
    private var radioGroup: RadioGroup? = null
    private var continueBtn: Button? = null
    private var paymentValue: EditText? = null
    private var startYear: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            instParam = arguments.get(INST_PARAM) as Institution?
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_rating_course_info, container, false)
        institutionName =  view.findViewById(R.id.institution_name_tv_ci) as TextView
        institutionImage = view.findViewById(R.id.institution_small_image_ci) as ImageView
        courseEt = view.findViewById(R.id.course_et_ci) as EditText
        radioGroup = view.findViewById(R.id.type_group_ci) as RadioGroup
        continueBtn = view.findViewById(R.id.continue_btn_ci) as Button
        paymentValue = view.findViewById(R.id.payed_value_et_ci) as EditText
        startYear = view.findViewById(R.id.start_year_et_ci) as EditText

        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        institutionName?.setText(instParam?.name)
        //TODO: set image
        continueBtn?.setOnClickListener { goToNext() }
    }

    private fun goToNext(){
        val review = GeneralReview()
        review.course = courseEt?.text.toString()
        review.institution = instParam
        review.payment = paymentValue?.text.toString().toDouble()
        review.startYear = startYear?.text.toString().toInt()
        //TODO: Get type or add it to course

        val adapter = (activity as RatingActivity).mPagerAdapter
        val pager = (activity as RatingActivity).mViewPager
        adapter?.add(RatingGeneralFragment.newInstance(review))
        pager?.setCurrentItem(pager.currentItem + 1, true)
    }

    companion object {
        private val INST_PARAM = "institution"
        fun newInstance(institution: Institution): RatingCourseInfoFragment {
            val fragment = RatingCourseInfoFragment()
            val args = Bundle()
            args.putParcelable(INST_PARAM, institution)
            fragment.arguments = args
            return fragment
        }
    }
}
