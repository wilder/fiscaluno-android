package com.fiscaluno.rating.generalReview

import android.util.Log
import com.fiscaluno.model.GeneralReview
import com.google.firebase.firestore.FirebaseFirestore


/**
 * Created by Wilder on 12/10/17.
 */

class GeneralReviewPresenter : GeneralReviewContract.Presenter{

    private lateinit var view: GeneralReviewContract.View
    private lateinit var db: FirebaseFirestore
    private val GENERAL_REVIEWS = "GeneralReviews"

    override fun bindView(view: GeneralReviewContract.View) {
        this.view = view
        db = FirebaseFirestore.getInstance()
    }

    //TODO: return error
    override fun saveGeneralReview(generalReview: GeneralReview) {
        //TODO: get the preferences and set the reviewedBy and createdAt here?
        db.collection(GENERAL_REVIEWS)
                .add(generalReview)
                .addOnSuccessListener {
                    Log.d(GENERAL_REVIEWS, "Review successfully written!")
                }
                .addOnFailureListener {
                    e ->
                    run {
                        Log.w(GENERAL_REVIEWS, "Error writing general review", e)
                        view.error(e.message!!)
                    }
                }
    }

}
