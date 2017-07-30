package com.fiscaluno.presenter

import com.fiscaluno.contracts.DetailedReviewContract
import com.fiscaluno.model.DetailedReview

/**
 * Created by Wilder on 25/07/17.
 */
class DetailedReviewPresenter : DetailedReviewContract.Presenter {

    var view: DetailedReviewContract.View? = null

    override fun bindView(view: DetailedReviewContract.View) {
        this.view = view
    }

    override fun loadReviews() {
        //TODO: Get reviews list
        val reviews  = java.util.ArrayList<DetailedReview>()

        for(i in 0..10){
            var review = DetailedReview()
            review.type = "Test $i"
            reviews.add(review)
        }

        view?.setupDetailedReviewsList(reviews)
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