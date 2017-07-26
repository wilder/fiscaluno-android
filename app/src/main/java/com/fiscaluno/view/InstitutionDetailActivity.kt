package com.fiscaluno.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.fiscaluno.R
import com.fiscaluno.model.Institution
import kotlinx.android.synthetic.main.activity_institution_detail.*

class InstitutionDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_institution_detail)
        intent.getParcelableExtra<Institution>("institution")
    }


}
