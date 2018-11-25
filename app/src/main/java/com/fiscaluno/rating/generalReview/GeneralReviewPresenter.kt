package com.fiscaluno.rating.generalReview

import com.fiscaluno.model.GeneralReview
import com.fiscaluno.network.FiscalunoApi
import com.google.firebase.firestore.FirebaseFirestore
import com.stepstone.stepper.StepperLayout
import org.kodein.di.Kodein
import org.kodein.di.generic.instance
import java.util.*


/**
 * Created by Wilder on 12/10/17.
 */

class GeneralReviewPresenter(val kodein: Kodein) : GeneralReviewContract.Presenter{

    private lateinit var view: GeneralReviewContract.View
    private val api: FiscalunoApi by kodein.instance()
    private lateinit var db: FirebaseFirestore
    private val GENERAL_REVIEWS = "GeneralReview"


    override fun bindView(view: GeneralReviewContract.View) {
        this.view = view
        this.db = FirebaseFirestore.getInstance()
    }

    override fun saveGeneralReview(generalReview: GeneralReview, callback: StepperLayout.OnNextClickedCallback?) {
        val generalReviewDTO = generalReview.copy()
        val collection = db.collection(GENERAL_REVIEWS)
        generalReviewDTO.courseId = generalReview.courseInfo?.courseId!!
        generalReviewDTO.courseInfo = null
        generalReviewDTO.institutionId = generalReview.institutionId
        generalReviewDTO.rate = generalReview.rate
        generalReviewDTO.createdAt = Date()
        collection.add(generalReviewDTO)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        view.success(generalReview, callback)
                    }
                    else {
                        view.error("Unable to save review")
                    }
                }
    }
}
