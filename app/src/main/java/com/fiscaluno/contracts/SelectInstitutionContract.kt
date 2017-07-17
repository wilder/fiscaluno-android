package com.fiscaluno.contracts

import com.fiscaluno.model.Institution

import java.util.ArrayList

/**
 * Created by Wilder on 16/07/17.
 */

interface SelectInstitutionContract {
    interface View {
        fun updateInstitutionList(institutions: ArrayList<Institution>)
    }

    interface Presenter {
        fun bindView(view: SelectInstitutionContract.View)
        fun loadMainInstitutions()
        fun searchInstitutions(search: String)
    }
}
