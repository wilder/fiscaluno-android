package com.fiscaluno.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v7.widget.Toolbar
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v4.view.GravityCompat
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import com.fiscaluno.App
import com.fiscaluno.R
import com.fiscaluno.R.id.*
import com.fiscaluno.contracts.MainContract
import com.fiscaluno.helper.PreferencesManager
import com.fiscaluno.model.Course
import com.fiscaluno.model.Institution
import com.fiscaluno.presenter.MainPresenter
import com.fiscaluno.rating.RatingActivity
import com.fiscaluno.view.adapter.TopCoursesAdapter
import com.fiscaluno.view.adapter.TopInstitutionsAdapter
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.search_panel.*
import android.content.DialogInterface
import android.R.string.ok
import android.view.LayoutInflater





class MainActivity : AppCompatActivity(), MainContract.View, NavigationView.OnNavigationItemSelectedListener{

    private var presenter: MainContract.Presenter? =  null
    private var topInstitutionsAdapter: TopInstitutionsAdapter? = null
    private var userInstitution: Institution? = null
    private var preferences: PreferencesManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val kodein = (application as App).kodein

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.open, R.string.close)
        drawer.setDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        toggleButtonLayout.setToggled(R.id.toggle_institutions,  true)

        presenter = MainPresenter(kodein)
        presenter?.bindView(this)
        //TODO: Call in different threads?
        presenter?.loadTopInstitutions()
        presenter?.loadTopCourses()
        preferences = PreferencesManager(this)
    }

    override fun showTopInstitutions(institutions: List<Institution>?) {
        topInstitutionsAdapter = TopInstitutionsAdapter(ArrayList(institutions), this)
        topInstitutionsRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        topInstitutionsRv.adapter = topInstitutionsAdapter
    }

    override fun showTopCourses(courses: List<Course>?) {
        val topCoursesAdapter = TopCoursesAdapter(ArrayList(courses), this)
        topCoursesRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        topCoursesRv.adapter = topCoursesAdapter
    }

    fun fbReviewClick(view: View) {
        val intent = Intent(this, RatingActivity::class.java)
        startActivity(intent)
    }

    fun fbSearchInstitutionClick(view: View) {
        //TODO: Go to search passing type as institution
        val intent = Intent(this, RatingActivity::class.java)
        startActivity(intent)
    }

    fun fbSearchCourseClick(view: View) {
        //TODO: Go to search passing type as course
        val intent = Intent(this, RatingActivity::class.java)
        startActivity(intent)
    }

    fun showFilterDialog(view: View) {
        //TODO: move to dialog manager class
        val builder = AlertDialog.Builder(this)


        builder.setView(layoutInflater.inflate(R.layout.dialog_search_filter, null))
                .setPositiveButton(R.string.ok, { dialog, id ->


                })
                .setNegativeButton(R.string.cancel, { dialog, id ->
                    dialog.cancel()
                })

        builder.create()
                .show()
    }

    fun goToUserInstitution(view: View){
        val intent = Intent(this, InstitutionDetailActivity::class.java)
        intent.putExtra("institution", userInstitution)
        startActivity(intent)
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var intent: Intent? = null
        when (item.itemId) {
            R.id.nav_profile -> {
                intent = Intent(this, RatingActivity::class.java)
                startActivity(intent)
            }

            R.id.nav_review -> {
                intent = Intent(this, RatingActivity::class.java)
                startActivity(intent)
            }

            R.id.nav_info -> {
                intent = Intent(this, RatingActivity::class.java)
                startActivity(intent)
            }

        }
        return true
    }

    override fun onBackPressed() {
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

}
