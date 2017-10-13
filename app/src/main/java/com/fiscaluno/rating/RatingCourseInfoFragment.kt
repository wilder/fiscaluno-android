package com.fiscaluno.rating

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
import com.fiscaluno.model.Institution
import com.stepstone.stepper.BlockingStep
import com.stepstone.stepper.StepperLayout
import com.stepstone.stepper.VerificationError

class RatingCourseInfoFragment : Fragment(), BlockingStep {

    private var instParam: Institution? = null
    private var institutionName: TextView? = null
    private var institutionImage: ImageView? = null
    private var courseEt: EditText? = null
    private var radioGroup: RadioGroup? = null
    private var paymentValue: EditText? = null
    private var startYear: EditText? = null
    private lateinit var dataManager: DataManager

    companion object {
        fun newInstance(): RatingCourseInfoFragment {
            val fragment = RatingCourseInfoFragment()
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

        val view = inflater!!.inflate(R.layout.fragment_rating_course_info, container, false)
        institutionName =  view.findViewById(R.id.institution_name_tv_ci) as TextView
        institutionImage = view.findViewById(R.id.institution_small_image_ci) as ImageView
        courseEt = view.findViewById(R.id.course_et_ci) as EditText
        radioGroup = view.findViewById(R.id.type_group_ci) as RadioGroup
        paymentValue = view.findViewById(R.id.payed_value_et_ci) as EditText
        startYear = view.findViewById(R.id.start_year_et_ci) as EditText

        return view
    }

    override fun onSelected() {
        instParam = dataManager.getInstitution()
        institutionName?.text = instParam?.name
    }

    override fun onNextClicked(callback: StepperLayout.OnNextClickedCallback?) {
        val review = GeneralReview()
        review.course = courseEt?.text.toString()
        dataManager.saveInstitution(instParam)
        review.payment = paymentValue?.text.toString().toDouble()
        review.startYear = startYear?.text.toString().toInt()
        (activity as RatingActivity).saveInstanceStateGeneralReview(review)
        callback?.goToNextStep()
    }

    override fun verifyStep(): VerificationError? {
        var canGoToNext = true
        if (courseEt?.text == null || courseEt?.text.toString().equals("")){
            courseEt?.error = "This field must be filed"
            canGoToNext = false
        }

        if (paymentValue?.text == null || paymentValue?.text.toString().equals("")){
            paymentValue?.error = "This field must be filed"
            canGoToNext = false
        }

        if (startYear?.text == null || startYear?.text.toString().equals("")){
            startYear?.error = "This field must be filed"
            canGoToNext = false
        }

        //TODO: check radiogroup

        if (!canGoToNext) {
            return VerificationError("teste")
        }

        return null
    }

    override fun onBackClicked(callback: StepperLayout.OnBackClickedCallback?) {
        callback?.goToPrevStep()
    }

    override fun onCompleteClicked(callback: StepperLayout.OnCompleteClickedCallback?) {
        callback?.complete()
    }

    override fun onError(error: VerificationError) {
    }

}
