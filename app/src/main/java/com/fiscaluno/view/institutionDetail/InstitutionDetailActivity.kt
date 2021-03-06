package com.fiscaluno.view.institutionDetail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import com.fiscaluno.R
import kotlinx.android.synthetic.main.activity_institution_detail2.*
import com.fiscaluno.view.adapter.ViewPagerAdapter
import android.support.v4.view.ViewPager
import com.fiscaluno.model.Institution
import kotlinx.android.synthetic.main.institution_info_panel.*
import android.support.design.widget.AppBarLayout
import android.view.MenuItem
import android.support.v4.app.NavUtils
import android.support.v7.app.ActionBar
import android.widget.Toolbar

class InstitutionDetailActivity : AppCompatActivity() {

    lateinit var institution: Institution

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_institution_detail2)

        setSupportActionBar(htab_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        institution = intent.extras.getParcelable("institution")
        institution.let {
            institutionNameTv.text = it.name
            ratedByTv.text = it.ratedByCount.toString()
            institutionIv.setImageURI(it.imageUri)
            averageRatingTv.text = it.averageRating.toString()
        }

        tabLayout.setupWithViewPager(institutionDetailViewPager)
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {}
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabSelected(tab: TabLayout.Tab?) {
                institutionDetailViewPager.currentItem = tab!!.position
            }
        })

        appBarLayout.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            internal var isShow = true
            internal var scrollRange = -1

            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.totalScrollRange
                }
                if (scrollRange + verticalOffset * 1.5 <= 0) {
                    collapsingToolbar.title = institution.name
                    isShow = true
                } else if (isShow) {
                    collapsingToolbar.title = " "
                    isShow = false
                }
            }
        })

        setupViewPager(institutionDetailViewPager)
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.add(InstitutionFragment.newInstance(institution), getString(R.string.reviews))
        adapter.add(InstitutionCourseFragment.newInstance(institution), getString(R.string.courses))
        adapter.add(ExamsFragment(), getString(R.string.exams))
        viewPager.adapter = adapter
        viewPager.offscreenPageLimit = adapter.count
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                super.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}

