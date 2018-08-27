package com.fiscaluno.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import com.fiscaluno.R
import kotlinx.android.synthetic.main.activity_institution_detail2.*
import com.fiscaluno.view.adapter.ViewPagerAdapter
import android.support.v4.view.ViewPager
import com.fiscaluno.view.institutionDetail.InstitutionFragment


class InstitutionDetail2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_institution_detail2)

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
        adapter.add(InstitutionFragment(), getString(R.string.institutions))
        adapter.add(InstitutionFragment(), getString(R.string.course))
        adapter.add(InstitutionFragment(), getString(R.string.exams))
        viewPager.adapter = adapter
    }

}

