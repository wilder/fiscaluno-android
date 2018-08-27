package com.fiscaluno.contracts

import com.fiscaluno.model.DetailedReview
import com.fiscaluno.model.GeneralReview
import com.fiscaluno.model.Institution

/**
 * Created by Wilder on 16/07/17.
 */

interface InstitutionDetailContract {
    interface View {
        fun setupGeneralReviews(generalReviews: List<GeneralReview>?)
        fun setupDetailedReviews(detailedReviews: List<DetailedReview>?)
    }

    interface Presenter {
        fun bindView(view: InstitutionDetailContract.View)
        fun loadGeneralReviews(institutionId: String)
        fun loadDetailedReviews(institutionId: String)
    }
}
