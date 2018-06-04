package com.fiscaluno.view.adapter

import android.content.Context
import android.support.v4.app.FragmentManager
import com.fiscaluno.rating.RatingCourseInfoFragment
import com.fiscaluno.rating.detailedReview.RatingDetailedFragment
import com.fiscaluno.rating.generalReview.RatingGeneralFragment
import com.fiscaluno.rating.RatingSelectInstitutionFragment
import com.stepstone.stepper.Step
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter


/**
 * Created by Wilder on 05/08/17.
 */
class StepperAdapter(fm: FragmentManager, context: Context) : AbstractFragmentStepAdapter(fm, context) {

    override fun createStep(position: Int): Step {
        return when (position) {
            0 -> RatingSelectInstitutionFragment.newInstance()
            1 -> RatingCourseInfoFragment.newInstance()
            2 -> RatingGeneralFragment.newInstance()
            3 -> RatingDetailedFragment.newInstance()
            else -> throw IllegalArgumentException("Unsupported position: $position")
        }
    }

    override fun getCount(): Int = 4
}