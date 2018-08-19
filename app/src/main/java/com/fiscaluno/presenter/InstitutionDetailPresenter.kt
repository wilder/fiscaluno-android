package com.fiscaluno.presenter

import com.fiscaluno.contracts.InstitutionDetailContract
import com.fiscaluno.model.DetailedReview
import com.fiscaluno.model.GeneralReview
import com.fiscaluno.model.Institution
import java.util.*

/**
 * Created by Wilder on 30/07/17.
 */
class InstitutionDetailPresenter : InstitutionDetailContract.Presenter {

    private var view: InstitutionDetailContract.View? = null

    override fun bindView(view: InstitutionDetailContract.View) {
        this.view = view
    }

    override fun loadInstitution(institutionId: String) {
        //TODO: Load average detailed review ratings from the institution
        val detailedReviews  = java.util.ArrayList<DetailedReview>()

        for(i in 0..4){
            var review = DetailedReview()
            review.type = "Test $i"
            review.rate = i+(0.03f*i)
            detailedReviews.add(review)
        }


        val generalReviews  = java.util.ArrayList<GeneralReview>()

        for(i in 0..4){
            var generalReview = GeneralReview()
            generalReview.pros = "pros pros pros pros $i"
            generalReview.cons = "Cons cons cons cons $i"
            generalReview.rate = i+(0.03f*i)
            generalReview.createdAt = Date()
            generalReview.startYear = 2000 + i
            generalReview.suggestion = "Suggestion, Suggestion, Suggestion, Suggestion"
            generalReview.payment = 400.toDouble()
            generalReviews.add(generalReview)
        }
        val institution = Institution(
                "asfoasdfha",
                "Faculdade Impacta Tecnologia",
                "1239123912",
                "0",
                listOf("contato@impacta.edu.br"),
                listOf("+551112381234"),
                "São Paulo",
                "http://faculdadeimpacta.edu/",
                "http://faculdadeimpacta.edu/logo.png",
                4.5f,
                300,
                detailedReviews,
                generalReviews)


        view?.setupInstitutionDetails(institution)
    }
}