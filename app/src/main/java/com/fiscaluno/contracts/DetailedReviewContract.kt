package com.fiscaluno.contracts

import com.fiscaluno.model.DetailedReview
import com.fiscaluno.model.GeneralReview
import java.util.*

/**
 * Created by Wilder on 16/07/17.
 */

interface DetailedReviewContract {
    interface View {
        fun setupDetailedReviewsList(review: ArrayList<DetailedReview>)
    }

    interface Presenter {
        fun bindView(view: DetailedReviewContract.View)
        fun loadReviewTypes()
        fun loadReviewsByInstitutionId(institutionId: String)
        fun saveDetailedReviews(detailedReviews: ArrayList<DetailedReview>, generalReview: GeneralReview)
    }
}
