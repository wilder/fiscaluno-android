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
import com.fiscaluno.contracts.MainContract
import com.fiscaluno.helper.PreferencesManager
import com.fiscaluno.model.Course
import com.fiscaluno.model.Institution
import com.fiscaluno.presenter.MainPresenter
import com.fiscaluno.rating.RatingActivity
import com.fiscaluno.view.adapter.CoursesAdapter
import com.fiscaluno.view.adapter.InstitutionsAdapter
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.search_panel.*
import kotlinx.android.synthetic.main.dialog_search_filter.view.*
import android.widget.SeekBar
import com.fiscaluno.model.SearchFilter
import com.fiscaluno.model.SearchableEntity
import android.support.v7.widget.PagerSnapHelper
import android.support.v7.widget.SnapHelper




class MainActivity : AppCompatActivity(), MainContract.View, NavigationView.OnNavigationItemSelectedListener{

    private var presenter: MainContract.Presenter? =  null
    private var institutionsAdapter: InstitutionsAdapter? = null
    private var userInstitution: Institution? = null
    private var preferences: PreferencesManager? = null
    private var searchFilter: SearchFilter = SearchFilter()
    private var selectedToggleFilter = R.id.toggle_institutions
    private val snapHelper = PagerSnapHelper()
    var searchableEntity: SearchableEntity? = null

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
        toggleButtonLayout.onToggledListener = { toggle, selected ->
            selectedToggleFilter = toggle.id
            searchableEntity = null
        }

        presenter = MainPresenter(kodein)
        presenter?.bindView(this)
        //TODO: Call in different threads?
        presenter?.loadTopInstitutions()
        presenter?.loadTopCourses()
        preferences = PreferencesManager(this)
    }

    override fun showTopInstitutions(institutions: List<Institution>?) {
        institutionsAdapter = InstitutionsAdapter(ArrayList(institutions), this)
        topInstitutionsRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        topInstitutionsRv.adapter = institutionsAdapter
        snapHelper.attachToRecyclerView(topInstitutionsRv)
    }

    override fun showTopCourses(courses: List<Course>?) {
        val topCoursesAdapter = CoursesAdapter(ArrayList(courses), this)
        topCoursesRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        topCoursesRv.adapter = topCoursesAdapter
        snapHelper.attachToRecyclerView(topInstitutionsRv)
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

        val dialogView = layoutInflater.inflate(R.layout.dialog_search_filter, null)

        if (selectedToggleFilter == R.id.toggle_institutions) {
            dialogView.textInputLayout.visibility = View.GONE
        }

        dialogView.ratingBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onProgressChanged(seekBar: SeekBar, progress: Int,
                                           fromUser: Boolean) {
                dialogView.tvMinRating.text = progress.toString()
            }
        });

        builder.setView(dialogView)
                .setPositiveButton(R.string.ok, { dialog, id ->
                    with(dialogView){

                        if (selectedToggleFilter == R.id.toggle_courses){
                            val course = Course()
                            course.institution = Institution(name = tip_institution_name.text.toString())
                            course.name = et_search.text.toString()
                            searchableEntity = course
                        }

                        searchFilter = SearchFilter(
                                searchableEntity = searchableEntity,
                                city = etCity.text.toString(),
                                state = etState.text.toString(),
                                rate = tvMinRating.text.toString().toFloat()
                        )

                    }
                })
                .setNegativeButton(R.string.cancel, { dialog, id ->
                    dialog.cancel()
                })

        builder.create()
                .show()
    }

    fun search(view: View) {
        val intent = Intent(this, SearchActivity::class.java)
        if(searchableEntity == null){
            if (selectedToggleFilter == R.id.toggle_courses){
                val course = Course()
                course.name = et_search.text.toString()
                searchableEntity = course
            } else if (selectedToggleFilter == R.id.toggle_institutions) {
                searchableEntity = Institution(name = et_search.text.toString())
            }
        }
        searchableEntity?.setValue(et_search.text.toString())
        searchFilter.searchableEntity = searchableEntity
        intent.putExtra("searchFilterExtra", searchFilter)
        startActivity(intent)
        searchableEntity = null
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
            this.moveTaskToBack(true)
        }
    }

}
