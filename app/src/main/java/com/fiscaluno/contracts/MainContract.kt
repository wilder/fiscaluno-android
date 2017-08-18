package com.fiscaluno.contracts

import android.content.SharedPreferences
import com.fiscaluno.model.Institution

/**
 * Created by Wilder on 14/08/17.
 */
interface MainContract {
    interface View {
        fun showTopInstitutions(institutions: List<Institution>)
        fun showUserInstitutionInfo(userInstitution: Institution?)
    }
    interface Presenter {
        fun bindView(view: MainContract.View)
        fun loadTopInstitutions()
        fun loadUserInstitutionInfo(institutionId: String?)
    }
}