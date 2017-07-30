package com.fiscaluno.contracts

import com.fiscaluno.model.DetailedReview
import com.fiscaluno.model.Institution

import java.util.ArrayList

/**
 * Created by Wilder on 16/07/17.
 */

interface DetailedReviewContract {
    interface View {
        fun setupReviewsList(review: ArrayList<DetailedReview>)
    }

    interface Presenter {
        fun bindView(view: DetailedReviewContract.View)
        fun loadReviews()
        fun loadReviewsByInstitutionId(institutionId: String)
    }
}
