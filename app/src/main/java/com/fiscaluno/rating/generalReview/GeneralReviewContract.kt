package com.fiscaluno.rating.generalReview

import com.fiscaluno.model.GeneralReview
import com.stepstone.stepper.StepperLayout

/**
 * Created by Wilder on 12/10/17.
 */

interface GeneralReviewContract {
    interface View {
        fun error(message: String)
        fun success(review: GeneralReview, callback: StepperLayout.OnNextClickedCallback?)
    }

    interface Presenter {
        fun saveGeneralReview(generalReview: GeneralReview, callback: StepperLayout.OnNextClickedCallback?)
        fun bindView(view: View)
    }
}
