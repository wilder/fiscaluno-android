package com.fiscaluno.contracts

import com.fiscaluno.model.Institution

import kotlin.collections.ArrayList

/**
 * Created by Wilder on 16/07/17.
 */

interface SelectInstitutionContract {
    interface View {
        fun updateInstitutionList(institutions: ArrayList<Institution>)
        //TODO: Change to list of Institutions
        fun setupInstitutionAutocomplete(institutions: ArrayList<Institution>)
    }

    interface Presenter {
        fun bindView(view: SelectInstitutionContract.View)
        fun loadMainInstitutions()
        fun searchInstitutions()
    }
}
