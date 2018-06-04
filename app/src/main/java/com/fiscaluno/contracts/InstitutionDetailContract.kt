package com.fiscaluno.contracts

import com.fiscaluno.model.Institution

/**
 * Created by Wilder on 16/07/17.
 */

interface InstitutionDetailContract {
    interface View {
        fun setupInstitutionDetails(institution: Institution)
    }

    interface Presenter {
        fun bindView(view: InstitutionDetailContract.View)
        fun loadInstitution(institutionId: String)
    }
}
