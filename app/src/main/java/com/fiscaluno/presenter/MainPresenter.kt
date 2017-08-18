package com.fiscaluno.presenter

import android.content.SharedPreferences
import com.fiscaluno.contracts.MainContract
import com.fiscaluno.model.Institution

/**
 * Created by Wilder on 14/08/17.
 */
class MainPresenter : MainContract.Presenter{

    lateinit var view: MainContract.View

    override fun bindView(view: MainContract.View) {
        this.view = view
    }

    /**
     * Loads the top rated Institutions
     */
    override fun loadTopInstitutions() {
        //TODO: Load top Institutions
        val topInstitutions: ArrayList<Institution> = ArrayList()
        for (i in 0..5) {
            val institution = Institution()
            institution.name = "test $i"
            institution.averageRating = i * 0.9f
            institution.id = i
            institution.reviewdBy = 132 * i
            topInstitutions?.add(institution)
        }
        view.showTopInstitutions(topInstitutions!!)
    }

    /**
     * TODO: handle multiple institutions
     * Loads the id of the user's institution
     *
     * The view will handle when the institution is null
     * and display a card informing the user that he doesn'
     * have a institution yet
     */
    override fun loadUserInstitutionInfo(institutionId: String?){

        var institution: Institution? = null

        if (institutionId != null) {

            //TODO: Get user's institution
            institution = Institution()
            institution.name = "Faculdade Impacta de Tecnologia"
            institution.averageRating = 3.12f * 0.9f
            institution.id = 2
            institution.reviewdBy = 132 * 5

        }

        view.showUserInstitutionInfo(institution)

    }
}