package com.fiscaluno.rating.generalReview

import com.fiscaluno.model.GeneralReview

/**
 * Created by Wilder on 12/10/17.
 */

interface GeneralReviewContract {
    interface View {
        fun error(message: String)
    }

    interface Presenter {
        fun saveGeneralReview(generalReview: GeneralReview)
        fun bindView(view: View)
    }
}
