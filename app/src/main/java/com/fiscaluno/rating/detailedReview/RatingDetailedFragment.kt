package com.fiscaluno.rating.detailedReview

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.fiscaluno.R
import com.fiscaluno.contracts.DataManager
import com.fiscaluno.contracts.DetailedReviewContract
import com.fiscaluno.helper.PreferencesManager
import com.fiscaluno.model.DetailedReview
import com.fiscaluno.model.GeneralReview
import com.fiscaluno.model.Institution
import com.fiscaluno.view.MainActivity
import com.stepstone.stepper.BlockingStep
import com.stepstone.stepper.StepperLayout
import com.stepstone.stepper.VerificationError
import java.util.ArrayList

class RatingDetailedFragment : Fragment(), DetailedReviewContract.View, BlockingStep {

    private var generalReview: GeneralReview? = null
    private var institutionName: TextView? = null
    private var institutionImage: ImageView? = null
    private var reviewsList: RecyclerView? = null
    private var adapter: DetailedReviewAdapter? = null
    private var presenter: DetailedReviewContract.Presenter? = null
    lateinit var dataManager: DataManager

    companion object {
        fun newInstance(): RatingDetailedFragment {
            val fragment = RatingDetailedFragment()
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = DetailedReviewPresenter()
        presenter?.bindView(this)
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

    override fun onSelected() {
        generalReview = dataManager.getGeneralReview()
        institutionName?.setText(generalReview?.institution?.name)
        institutionImage?.setImageDrawable(resources.getDrawable(R.mipmap.ic_launcher)) //TODO: get image
        presenter?.loadReviews()
    }


    override fun setupDetailedReviewsList(review: ArrayList<DetailedReview>) {
        setupList(review)
    }

    private fun setupList(reviews: ArrayList<DetailedReview>){
        adapter = DetailedReviewAdapter(reviews, true)
        reviewsList?.adapter = adapter
        reviewsList?.layoutManager = LinearLayoutManager(context) as RecyclerView.LayoutManager?
    }

    override fun onBackClicked(callback: StepperLayout.OnBackClickedCallback?) {
        callback?.goToPrevStep()
    }

    override fun onCompleteClicked(callback: StepperLayout.OnCompleteClickedCallback?) {
        //activity.fragmentManager.popBackStack()
        if (check()) {
            //TODO: Move to presenter and Save
            PreferencesManager(context).userInstitutionId = generalReview?.institution?.id.toString()
            startActivity(Intent(activity, MainActivity::class.java))
        }
    }

    /**
     *  Checks if all rating bars have been clicked
     */
    private fun check() : Boolean {
        val reviews = adapter?.getDetailedReviews()
        reviews!!.forEach {
            i ->
            if (i.rate == 0.0f || i.rate == null) {
                //TODO: display message
                return false
            }
        }
        return true
    }

    override fun onNextClicked(callback: StepperLayout.OnNextClickedCallback?) {

    }

    override fun verifyStep(): VerificationError? {
        return null
    }

    override fun onError(error: VerificationError) {
    }
}
