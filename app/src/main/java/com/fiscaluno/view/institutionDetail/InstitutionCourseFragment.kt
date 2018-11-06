package com.fiscaluno.view.institutionDetail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fiscaluno.App

import com.fiscaluno.R
import com.fiscaluno.contracts.CourseContract
import com.fiscaluno.model.Course
import com.fiscaluno.model.Institution
import com.fiscaluno.presenter.CoursePresenter
import com.fiscaluno.view.adapter.CoursesAdapter
import kotlinx.android.synthetic.main.fragment_institution_course.*

private const val INSTITUTION_PARAM = "param1"

class InstitutionCourseFragment : Fragment(), CourseContract.View {

    private lateinit var institution: Institution
    private lateinit var presenter: CourseContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            institution = it.getParcelable(INSTITUTION_PARAM)
        }

        val kodein = (activity?.application as App).kodein

        presenter = CoursePresenter(kodein)
        presenter.bindView(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //TODO: handle pagination
        presenter.findInstitutionCourses(institution.id!!)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_institution_course, container, false)
    }

    override fun showInstitutionCourses(searchResult: List<Course>?) {
        val coursesAdapter = CoursesAdapter(ArrayList(searchResult), context!!)
        rvCourses.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvCourses.adapter = coursesAdapter
    }

    companion object {
        @JvmStatic
        fun newInstance(institution: Institution) =
                InstitutionCourseFragment().apply {
                    arguments = Bundle().apply {
                        putParcelable(INSTITUTION_PARAM, institution)
                    }
                }
    }
}
