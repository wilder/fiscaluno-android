package com.fiscaluno.view.courseDetail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.view.MenuItem
import com.fiscaluno.R
import com.fiscaluno.model.Course
import com.fiscaluno.repository.AnonymousUserTracker
import com.fiscaluno.view.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_course_detail.*
import kotlinx.android.synthetic.main.course_info_panel.*

class CourseDetailActivity : AppCompatActivity() {

    lateinit var course: Course

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_detail)

        setSupportActionBar(htab_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        course = intent.extras.getParcelable("course")
        course.let {
            institutionNameTv.text = it.institution?.name
            courseNameTv.text = it.name
            ratedByTv.text = it.ratedByCount.toString()
            institutionIv.setImageURI(it.institution?.imageUri)
            averageRatingTv.text = it.averageRating.toString()
        }

        tabLayout.setupWithViewPager(courseDetailViewPager)
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {}
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabSelected(tab: TabLayout.Tab?) {
                courseDetailViewPager.currentItem = tab!!.position
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
                    collapsingToolbar.title = course.name
                    isShow = true
                } else if (isShow) {
                    collapsingToolbar.title = " "
                    isShow = false
                }
            }
        })

        setupViewPager(courseDetailViewPager)
        AnonymousUserTracker.trackAccessIfNotLoggedIn(this)
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.add(CourseInfoFragment.newInstance(course), getString(R.string.details))
        adapter.add(CourseReviewFragment.newInstance(course), getString(R.string.reviews))
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
