package com.fiscaluno.contracts

import com.fiscaluno.model.GeneralReview

import java.util.ArrayList

/**
 * Created by Wilder on 16/07/17.
 */

interface InstitutionDetailContract {
    interface View : DetailedReviewContract.View {
        fun setupGeneralReviewsList(reviews: ArrayList<GeneralReview>)
    }

    interface Presenter {
        fun bindView(view: InstitutionDetailContract.View)
        fun loadInstitution(institutionId: String)
    }
}
