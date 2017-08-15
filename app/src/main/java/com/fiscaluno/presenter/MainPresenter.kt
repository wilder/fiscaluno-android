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

    override fun loadUserInstitutionInfo(sharedPreferences: SharedPreferences) {
        //TODO: Get user's institution
        val institution = Institution()
        institution.name = "Faculdade Impacta de Tecnologia"
        institution.averageRating = 3.12f * 0.9f
        institution.id = 2
        institution.reviewdBy = 132 * 5
        view.showUserInstitutionInfo(institution)
    }
}