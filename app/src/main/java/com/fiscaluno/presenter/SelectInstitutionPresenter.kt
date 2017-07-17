package com.fiscaluno.presenter

import com.fiscaluno.contracts.SelectInstitutionContract

/**
 * Created by Wilder on 16/07/17.
 */

class SelectInstitutionPresenter : SelectInstitutionContract.Presenter {

    internal var view: SelectInstitutionContract.View

    override fun bindView(view: SelectInstitutionContract.View) {
        this.view = view
    }

    override fun loadMainInstitutions() {
        view.updateInstitutionList(null)
    }

    override fun searchInstitutions(search: String) {
        view.updateInstitutionList(null)
    }
}
