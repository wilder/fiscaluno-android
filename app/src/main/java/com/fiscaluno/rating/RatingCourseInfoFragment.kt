package com.fiscaluno.rating

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.facebook.drawee.view.SimpleDraweeView
import com.fiscaluno.R
import com.fiscaluno.contracts.DataManager
import com.fiscaluno.model.CourseInfo
import com.fiscaluno.model.GeneralReview
import com.fiscaluno.model.Institution
import com.stepstone.stepper.BlockingStep
import com.stepstone.stepper.StepperLayout
import com.stepstone.stepper.VerificationError
import java.util.*

class RatingCourseInfoFragment : Fragment(), BlockingStep {

    private var instParam: Institution? = null
    private var institutionName: TextView? = null
    private var institutionImage: SimpleDraweeView? = null
    private var courseSpinner: Spinner? = null
    private var courseTypeRadioGroup: RadioGroup? = null
    private var coursePeriodRadioGroup: RadioGroup? = null
    private var paymentValue: EditText? = null
    private var startYearSpinner: Spinner? = null
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_rating_course_info, container, false)
        institutionName =  view.findViewById(R.id.institution_name_tv_ci)
        institutionImage = view.findViewById(R.id.institution_small_image_ci)
        courseSpinner = view.findViewById(R.id.course_spinner)
        courseTypeRadioGroup = view.findViewById(R.id.courseType)
        coursePeriodRadioGroup = view.findViewById(R.id.coursePeriod)
        paymentValue = view.findViewById(R.id.payed_value_et_ci)
        startYearSpinner = view.findViewById(R.id.startYearSp)

        institutionImage!!.setImageURI(instParam?.imageUri)
        setupYears(getAvailableYears())
        setupCourses(listOf("Sistemas de Informação", "Análise e Desenvolvimento de Sistemas"))


        return view
    }

    fun setupCourses(availableCourses: List<String>) {
        courseSpinner!!.adapter = ArrayAdapter(context, android.R.layout.simple_spinner_item,
                availableCourses)
    }

    fun setupYears(availableYears: List<Int>) {
        startYearSpinner!!.adapter = ArrayAdapter(context, android.R.layout.simple_spinner_item,
                availableYears)
    }

    private fun getAvailableYears(): List<Int> =
            IntRange(1970, Calendar.getInstance().get(Calendar.YEAR)).toList().reversed()

    override fun onSelected() {
        instParam = dataManager.getInstitution()
        institutionName?.text = instParam?.name
    }

    override fun onNextClicked(callback: StepperLayout.OnNextClickedCallback?) {
        val review = GeneralReview()
        review.courseInfo = CourseInfo(
                courseType = getSelectedRadioButtonValue(courseTypeRadioGroup!!),
                period = getSelectedRadioButtonValue(coursePeriodRadioGroup!!),
                startYear = startYearSpinner?.selectedItem.toString().toInt(),
                monthlyPaymentValue = paymentValue?.text.toString().toFloat(),
                courseName = courseSpinner?.selectedItem.toString()
        )
        Log.d("rtCourseInfo", review.courseInfo.toString())
        dataManager.saveInstitution(instParam)
        (activity as RatingActivity).saveInstanceStateGeneralReview(review)
        callback?.goToNextStep()
    }

    private fun getSelectedRadioButtonValue(radioGroup: RadioGroup) =
            view?.findViewById<RadioButton>(radioGroup.checkedRadioButtonId)?.text.toString()

    override fun verifyStep(): VerificationError? {
        var canGoToNext = true
        if (startYearSpinner?.selectedItem == null || startYearSpinner?.selectedItem.toString().isBlank()){
            //TODO: Show toast or error
            canGoToNext = false
        }

        if (paymentValue?.text == null || paymentValue?.text.toString().isBlank()){
            paymentValue?.error = "This field must be filed"
            canGoToNext = false
        }

        if (courseSpinner?.selectedItem == null || courseSpinner?.selectedItem.toString().isBlank()){
            //TODO: Show toast or error
            canGoToNext = false
        }

        //TODO: check radiogroup
        if (coursePeriodRadioGroup!!.checkedRadioButtonId == 0) {
            canGoToNext = false
        }

        if (courseTypeRadioGroup!!.checkedRadioButtonId == 0) {
            canGoToNext = false
        }

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
