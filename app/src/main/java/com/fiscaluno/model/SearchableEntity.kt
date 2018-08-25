package com.fiscaluno.model

import android.os.Parcelable
import com.fiscaluno.contracts.SearchContract

interface SearchableEntity : Parcelable {
    fun search(searchPresenter: SearchContract.Presenter, searchFilter: SearchFilter)
    fun setValue(name: String)
    fun getValue() : String
}
