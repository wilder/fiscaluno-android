package com.fiscaluno.contracts

import com.fiscaluno.model.GeneralReview
import com.fiscaluno.model.Institution

/**
 * Created by Wilder on 06/08/17.
 */

interface DataManager {

    fun saveInstitution(institution: Institution?)

    fun getInstitution(): Institution?

    fun saveGeneralReview(review: GeneralReview?)

    fun getGeneralReview(): GeneralReview?
}