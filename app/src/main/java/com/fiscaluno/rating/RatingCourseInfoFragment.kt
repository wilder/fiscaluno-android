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
import com.fiscaluno.App
import com.fiscaluno.R
import com.fiscaluno.contracts.DataManager
import com.fiscaluno.contracts.SearchContract
import com.fiscaluno.model.*
import com.fiscaluno.presenter.SearchPresenter
import com.fiscaluno.rating.generalReview.GeneralReviewContract
import com.fiscaluno.rating.generalReview.GeneralReviewPresenter
import com.stepstone.stepper.BlockingStep
import com.stepstone.stepper.StepperLayout
import com.stepstone.stepper.VerificationError
import java.util.*

class RatingCourseInfoFragment : Fragment(), SearchContract.View, BlockingStep {

    private var instParam: Institution? = null
    private var institutionName: TextView? = null
    private var institutionImage: SimpleDraweeView? = null
    private var courseSpinner: Spinner? = null
    private var courseTypeRadioGroup: RadioGroup? = null
    private var coursePeriodRadioGroup: RadioGroup? = null
    private var paymentValue: EditText? = null
    private var startYearSpinner: Spinner? = null
    private lateinit var dataManager: DataManager
    private lateinit var presenter: SearchContract.Presenter

    companion object {
        fun newInstance(): RatingCourseInfoFragment = RatingCourseInfoFragment()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is DataManager) {
            dataManager = context
            val kodein = (activity?.application as App).kodein
            presenter = SearchPresenter(kodein)
            presenter.bindView(this)
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
        presenter.searchCourse(SearchFilter(instParam))
        setupYears(getAvailableYears())

        return view
    }


    override fun displayCourses(searchResult: List<Course>?) {
        val courses = searchResult?.map { it.name!! }?.toList()
        setupCourses(courses)
    }

    private fun setupCourses(availableCourses: List<String>?) {
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
                courseId = 1, // TODO: Get course id
                courseType = getSelectedRadioButtonValue(courseTypeRadioGroup!!),
                period = getSelectedRadioButtonValue(coursePeriodRadioGroup!!),
                startYear = startYearSpinner?.selectedItem.toString().toInt(),
                monthlyPaymentValue = paymentValue?.text.toString().toInt(),
                courseName = courseSpinner?.selectedItem.toString()
        )

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

    override fun displayInstitutions(searchResult: List<Institution>?) {
    }

}
