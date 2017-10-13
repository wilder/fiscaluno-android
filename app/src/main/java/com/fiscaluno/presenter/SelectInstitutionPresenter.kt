package com.fiscaluno.presenter

import com.fiscaluno.contracts.SelectInstitutionContract
import com.fiscaluno.model.Institution

/**
 * Created by Wilder on 16/07/17.
 */

class SelectInstitutionPresenter : SelectInstitutionContract.Presenter {

    var view: SelectInstitutionContract.View? = null

    override fun bindView(view: SelectInstitutionContract.View) {
        this.view = view
    }

    override fun loadMainInstitutions() {
        val institutions = ArrayList<Institution>()
        var i = 0
        while (i < 5) {
            val inst = Institution()
            inst.name = "Inst $i"
            inst.id = "bmtnYGMva25ljuXABdQx"
            institutions.add(inst)
            i+=1
        }
        view?.updateInstitutionList(institutions)
    }

    override fun searchInstitutions() {
        //TODO: Change to list of Institutions and get Institutions from Web Service
        val institutions = ArrayList<Institution>()
        var i = 0
        while (i < 5) {
            val inst = Institution()
            inst.name = "Inst $i"
            institutions.add(inst)
            i+=1
        }
        view?.setupInstitutionAutocomplete(institutions)
    }
}
