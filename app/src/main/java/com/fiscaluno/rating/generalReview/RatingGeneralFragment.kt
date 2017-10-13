package com.fiscaluno.rating.generalReview

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.fiscaluno.R
import com.fiscaluno.contracts.DataManager
import com.fiscaluno.helper.PreferencesManager
import com.fiscaluno.model.GeneralReview
import com.fiscaluno.model.Institution
import com.fiscaluno.rating.RatingActivity
import com.stepstone.stepper.BlockingStep
import com.stepstone.stepper.StepperLayout
import com.stepstone.stepper.VerificationError
import java.util.*

class RatingGeneralFragment : Fragment(), BlockingStep, GeneralReviewContract.View {

    private lateinit var presenter: GeneralReviewContract.Presenter
    private lateinit var institution: Institution
    private var review: GeneralReview? = null
    private var institutionImage: ImageView? = null
    private var institutionNameTv: TextView? = null
    private var reviewTitleEt: EditText? = null
    private var prosTv: EditText? = null
    private var consTv: EditText? = null
    private var suggestionsEt: EditText? = null
    private var ratingBar: RatingBar? = null
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

        presenter = GeneralReviewPresenter()
        presenter!!.bindView(this)

        val view = inflater!!.inflate(R.layout.fragment_rating_general, container, false)

        institutionImage = view.findViewById(R.id.institution_small_image_gn) as ImageView
        institutionNameTv = view.findViewById(R.id.institution_name_tv_gn) as TextView
        reviewTitleEt = view.findViewById(R.id.review_title_et_gn) as EditText
        prosTv = view.findViewById(R.id.pros_et_gn) as EditText
        consTv = view.findViewById(R.id.cons_et_gn) as EditText
        suggestionsEt = view.findViewById(R.id.suggestions_et_gn) as EditText
        ratingBar = view.findViewById(R.id.rating_stars_gn) as RatingBar

        return view
    }

    override fun error(message: String) {
        //TODO: display error message
    }

    override fun onSelected() {
        review = dataManager.getGeneralReview()
        institution = dataManager.getInstitution()!!
        institutionImage?.setImageDrawable(resources.getDrawable(R.mipmap.ic_launcher)) //TODO: Get institution image
        institutionNameTv?.text = institution.name
    }

    override fun verifyStep(): VerificationError? {
        var canGoToNext = true

        if (ratingBar!!.rating == 0.0f) {
            //TODO: display message
            canGoToNext = false
        }

        if (prosTv?.text == null || prosTv?.text.toString().equals("")){
            prosTv?.error = "This field must be filed"
            canGoToNext = false
        }

        if (suggestionsEt?.text == null || suggestionsEt?.text.toString().equals("")){
            suggestionsEt?.error = "This field must be filed"
            canGoToNext = false
        }

        if (consTv?.text == null || consTv?.text.toString().equals("")){
            consTv?.error = "This field must be filed"
            canGoToNext = false
        }

        if (reviewTitleEt?.text == null || reviewTitleEt?.text.toString().equals("")){
            reviewTitleEt?.error = "This field must be filed"
            canGoToNext = false
        }

        if (!canGoToNext) {
            return VerificationError("teste")
        }

        return null
    }

    override fun onNextClicked(callback: StepperLayout.OnNextClickedCallback) {
        //TODO: move to presenter
        review?.rate = ratingBar?.rating
        review?.description = reviewTitleEt?.text.toString()
        review?.cons = consTv?.text.toString()
        review?.pros = prosTv?.text.toString()
        review?.suggestion = suggestionsEt?.text.toString()
        review?.createdAt = Date()
        review?.studentId = PreferencesManager(context).user?.id
        review?.institutionId = institution.id
        (activity as RatingActivity).saveInstanceStateGeneralReview(review)

        presenter.saveGeneralReview(generalReview = review!!)

        Toast.makeText(this.context, review?.toString(), Toast.LENGTH_LONG).show()
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
