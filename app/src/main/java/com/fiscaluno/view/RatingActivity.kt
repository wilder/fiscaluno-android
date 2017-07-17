package com.fiscaluno.view

import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar

import android.support.v4.view.ViewPager
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

import com.fiscaluno.R
import com.fiscaluno.view.adapter.ViewPagerAdapter

class RatingActivity : AppCompatActivity() {

    public var mPagerAdapter: ViewPagerAdapter? = null

    var mViewPager: ViewPager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rating)

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        mPagerAdapter = ViewPagerAdapter(supportFragmentManager,
                listOf(RatingSelectInstitutionFragment.newInstance(),
                        RatingCourseInfoFragment.newInstance(""),
                        RatingGeneralFragment.newInstance(""),
                        RatingDetailedFragment.newInstance("")), resources.getString(R.string.title_activity_rating))

        mViewPager = findViewById(R.id.container) as ViewPager
        mViewPager!!.adapter = mPagerAdapter


        val fab = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_rating, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId


        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }


}
