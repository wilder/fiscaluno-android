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
        val institution = Institution()
        institution.address = "Avenida Rudge, 215"
        institution.cnpj = "1239123912"
        institution.id = "0"
        institution.name = "Faculdade Impacta Tecnologia"
        institution.imageUri = "http://faculdadeimpacta.edu/logo.png"
        institution.website = "http://faculdadeimpacta.edu/"
        institution.phoneNumber = "+551112381234"
        institution.email = "contato@impacta.edu.br"
        institution.averageRating = 4.5f
        institution.reviewdBy = 432

        //TODO: Load average detailed review ratings from the institution
        val detailedReviews  = java.util.ArrayList<DetailedReview>()

        for(i in 0..4){
            var review = DetailedReview()
            review.type = "Test $i"
            review.rate = i+(0.03f*i)
            detailedReviews.add(review)
        }

        institution.detailedReviews = detailedReviews

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
        institution.generalReviews = generalReviews
        institution.detailedReviews = detailedReviews

        view?.setupInstitutionDetails(institution)
    }
}