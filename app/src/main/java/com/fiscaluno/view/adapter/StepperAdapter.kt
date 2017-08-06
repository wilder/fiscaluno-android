package com.fiscaluno.view.adapter

import android.content.Context
import android.support.v4.app.FragmentManager
import com.fiscaluno.view.RatingCourseInfoFragment
import com.fiscaluno.view.RatingDetailedFragment
import com.fiscaluno.view.RatingGeneralFragment
import com.fiscaluno.view.RatingSelectInstitutionFragment
import com.stepstone.stepper.Step
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter


/**
 * Created by Wilder on 05/08/17.
 */
class StepperAdapter(fm: FragmentManager, context: Context) : AbstractFragmentStepAdapter(fm, context) {

    override fun createStep(position: Int): Step {
        when (position) {
            0 -> return RatingSelectInstitutionFragment.newInstance()
            1 -> return RatingCourseInfoFragment.newInstance()
            2 -> return RatingGeneralFragment.newInstance()
            3 -> return RatingDetailedFragment.newInstance()
            else -> throw IllegalArgumentException("Unsupported position: " + position)
        }
    }

    override fun getCount(): Int {
        return 4
    }
}