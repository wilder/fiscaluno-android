package com.fiscaluno.rating.detailedReview

import android.util.Log
import com.fiscaluno.contracts.DetailedReviewContract
import com.fiscaluno.model.DetailedReview
import com.fiscaluno.model.GeneralReview
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

/**
 * Created by Wilder on 25/07/17.
 */
class DetailedReviewPresenter : DetailedReviewContract.Presenter {

    private val DETAILED_REVIEWS = "DetailedReview"
    val DETAILED_REVIEW_TYPES_REFERENCE = "DetailedReviewTypes"
    val TAG = "DetailedReviewPresenter"

    var view: DetailedReviewContract.View? = null

    private lateinit var db: FirebaseFirestore

    override fun bindView(view: DetailedReviewContract.View) {
        this.view = view
        db = FirebaseFirestore.getInstance()
    }

    /**
     * load detailed review types from firestore
     */
    override fun loadReviewTypes() {
        val reviews  = java.util.ArrayList<DetailedReview>()

        db.collection(DETAILED_REVIEW_TYPES_REFERENCE)
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        reviews.clear()
                        for (type in task.result.toObjects(DetailedReviewTypes::class.java)) {
                            //task.result.toObjects(DetailedReviewTypes::class.java)
                            var review = DetailedReview()
                            review.type = type.description
                            reviews.add(review)
                        }
                    }
                    view?.setupDetailedReviewsList(reviews)
                }
    }

    override fun loadReviewsByInstitutionId(institutionId: String) {
        //TODO: Load average detailed review ratings from the institution
        val reviews  = java.util.ArrayList<DetailedReview>()

        for(i in 0..4){
            var review = DetailedReview()
            review.type = "Test $i"
            review.rate = i+(0.03f*i)
            reviews.add(review)
        }

    }

    override fun saveDetailedReviews(detailedReviews: ArrayList<DetailedReview>, generalReview: GeneralReview) {
        detailedReviews.forEach { review ->
            review.createdAt = Date()
            review.course = generalReview.course;
            review.institutionId = generalReview.institutionId;
            review.studentId = generalReview.studentId;
            db.collection(DETAILED_REVIEWS)
                    .add(review)
                    .addOnSuccessListener {
                        Log.d(DETAILED_REVIEWS, "Review successfully written!")
                    }
                    .addOnFailureListener {
                        e ->
                        run {
                            Log.w(DETAILED_REVIEWS, "Error writing general review", e)
                            //view.error(e.message!!) TODO: display error
                        }
                    }
        }
    }

}