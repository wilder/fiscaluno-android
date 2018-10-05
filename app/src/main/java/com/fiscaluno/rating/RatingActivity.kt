package com.fiscaluno.rating

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.fiscaluno.R
import com.fiscaluno.contracts.DataManager
import com.fiscaluno.model.GeneralReview
import com.fiscaluno.model.Institution
import com.fiscaluno.view.adapter.StepperAdapter
import com.stepstone.stepper.StepperLayout

class RatingActivity : AppCompatActivity(), DataManager {

    lateinit var mStepperLayout: StepperLayout
    private var institution: Institution? = null
    private var generalReview: GeneralReview? = null

    companion object {
        private const val CURRENT_STEP_POSITION_KEY = "position"
        private const val INSTITUTION = "institution"
        private const val GENERALREVIEW = "general_review"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rating)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val startingStepPosition = savedInstanceState?.getInt(CURRENT_STEP_POSITION_KEY) ?: 0
        institution = savedInstanceState?.getParcelable(INSTITUTION)
        generalReview = savedInstanceState?.getParcelable(GENERALREVIEW)

        mStepperLayout = findViewById(R.id.stepperLayout)
        mStepperLayout.setAdapter(StepperAdapter(supportFragmentManager, this), startingStepPosition)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(CURRENT_STEP_POSITION_KEY, mStepperLayout.currentStepPosition)
        outState.putParcelable(INSTITUTION, institution)
        outState.putParcelable(GENERALREVIEW, generalReview)
        super.onSaveInstanceState(outState)
    }

    override fun onBackPressed() {
        val currentStepPosition = mStepperLayout.currentStepPosition
        if (currentStepPosition > 0) {
            mStepperLayout.onBackClicked()
        } else {
            finish()
        }
    }

    fun proceedListener() {
        mStepperLayout.proceed()
    }

    override fun saveInstitution(institution: Institution?) {
        this.institution = institution
    }

    override fun getInstitution(): Institution? {
        return institution
    }

    override fun saveInstanceStateGeneralReview(review: GeneralReview?) {
        generalReview = review
    }

    override fun getGeneralReview(): GeneralReview? {
        return generalReview
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_rating, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == android.R.id.home) {
            super.onBackPressed()
            return true
        }

        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

}
