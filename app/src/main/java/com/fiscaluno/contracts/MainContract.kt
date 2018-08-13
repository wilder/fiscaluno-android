package com.fiscaluno.contracts

import com.fiscaluno.model.Institution

/**
 * Created by Wilder on 14/08/17.
 */
interface MainContract {
    interface View {
        fun showTopInstitutions(institutions: List<Institution>)
    }
    interface Presenter {
        fun bindView(view: MainContract.View)
        fun loadTopInstitutions()
    }
}