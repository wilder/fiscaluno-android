package com.fiscaluno.contracts

import com.fiscaluno.model.DetailedReview
import com.fiscaluno.model.GeneralReview
import com.stepstone.stepper.StepperLayout
import java.util.*

/**
 * Created by Wilder on 16/07/17.
 */

interface DetailedReviewContract {
    interface View {
        fun setupDetailedReviewsList(review: ArrayList<DetailedReview>)
        fun error(message: String)
        fun success(callback: StepperLayout.OnCompleteClickedCallback?)
    }

    interface Presenter {
        fun bindView(view: DetailedReviewContract.View)
        fun loadReviewTypes()
        fun saveDetailedReviews(detailedReviews: List<DetailedReview>, generalReview: GeneralReview,
                                callback: StepperLayout.OnCompleteClickedCallback?)
    }
}
