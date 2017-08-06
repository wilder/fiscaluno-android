package com.fiscaluno.view

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.fiscaluno.R
import com.fiscaluno.contracts.DataManager
import com.fiscaluno.model.GeneralReview
import com.stepstone.stepper.BlockingStep
import com.stepstone.stepper.Step
import com.stepstone.stepper.StepperLayout
import com.stepstone.stepper.VerificationError

class RatingGeneralFragment : Fragment(), BlockingStep {

    private var reviewParam: GeneralReview? = null
    private var institutionImage: ImageView? = null
    private var institutionNameTv: TextView? = null
    private var reviewTitleEt: EditText? = null
    private var prosTv: EditText? = null
    private var consTv: EditText? = null
    private var suggestionsEt: EditText? = null
    private var ratingBar: RatingBar? = null
    private var addButton: Button? = null
    lateinit var dataManager: DataManager


    companion object {
        fun newInstance(): RatingGeneralFragment {
            val fragment = RatingGeneralFragment()
            return fragment
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is DataManager) {
            dataManager = context
        } else {
            throw IllegalStateException("Activity must implement DataManager interface!")
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

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

    override fun onSelected() {
        reviewParam = dataManager.getGeneralReview()
        institutionImage?.setImageDrawable(resources.getDrawable(R.mipmap.ic_launcher)) //TODO: Get institution image
        institutionNameTv?.text = reviewParam?.institution?.name
    }

    override fun verifyStep(): VerificationError? {
        //TODO: Validate Values
        return null
    }

    override fun onNextClicked(callback: StepperLayout.OnNextClickedCallback) {
        reviewParam?.rate = ratingBar?.rating
        reviewParam?.description = reviewTitleEt?.text.toString()
        reviewParam?.cons = consTv?.text.toString()
        reviewParam?.pros = prosTv?.text.toString()
        reviewParam?.suggestion = suggestionsEt?.text.toString()
        (activity as RatingActivity).saveGeneralReview(reviewParam)

        Toast.makeText(this.context, reviewParam?.toString(), Toast.LENGTH_LONG).show()
        callback.goToNextStep()
    }

    override fun onBackClicked(callback: StepperLayout.OnBackClickedCallback?) {
        callback?.goToPrevStep()
    }

    override fun onCompleteClicked(callback: StepperLayout.OnCompleteClickedCallback?) {
    }

    override fun onError(error: VerificationError) {
    }

}
