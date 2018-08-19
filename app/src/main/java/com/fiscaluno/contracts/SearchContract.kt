package com.fiscaluno.contracts

import com.fiscaluno.model.RateableEntity
import com.fiscaluno.model.SearchFilter

interface SearchContract {

    interface Presenter {
        fun search(searchFilter: SearchFilter, page: Int, pageSize: Int)
    }

    interface View {
        fun displaySerchResult(searchResult: List<RateableEntity>)
    }

}