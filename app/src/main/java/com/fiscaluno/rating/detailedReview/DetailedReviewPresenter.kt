package com.fiscaluno.rating.detailedReview

import com.fiscaluno.contracts.DetailedReviewContract
import com.fiscaluno.model.DetailedReview
import com.google.firebase.firestore.FirebaseFirestore

/**
 * Created by Wilder on 25/07/17.
 */
class DetailedReviewPresenter : DetailedReviewContract.Presenter {

    val DETAILED_REVIEW_TYPES_REFERENCE = "DetailedReviewTypes"
    val TAG = "DetailedReviewPresenter"


    var view: DetailedReviewContract.View? = null

    private lateinit var db: FirebaseFirestore

    override fun bindView(view: DetailedReviewContract.View) {
        this.view = view
        db = FirebaseFirestore.getInstance()
    }

    override fun loadReviews() {
        //TODO: Get reviews list
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

}