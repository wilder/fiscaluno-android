package com.fiscaluno.view.courseDetail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fiscaluno.App

import com.fiscaluno.R
import com.fiscaluno.contracts.ReviewsContract
import com.fiscaluno.model.Course
import com.fiscaluno.model.DetailedReview
import com.fiscaluno.model.GeneralReview
import com.fiscaluno.presenter.ReviewsPresenter
import com.fiscaluno.view.adapter.InstitutionDetailGeneralReviewsAdapter
import kotlinx.android.synthetic.main.fragment_course_review.*

private const val COURSE_PARAM = "param1"

class CourseReviewFragment : Fragment(), ReviewsContract.View {

    private lateinit var course: Course
    private lateinit var presenter: ReviewsContract.Presenter
    private lateinit var generalReviewAdapter: InstitutionDetailGeneralReviewsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_course_review, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            course = it.getParcelable(COURSE_PARAM)
        }

        presenter = ReviewsPresenter((activity?.application as App).kodein)
        presenter.bindView(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.loadDetailedReviews(course.institutionId, course.id)
        presenter.loadGeneralReviews(course.institutionId, course.id)
    }

    override fun setupGeneralReviews(generalReviews: List<GeneralReview>?) {
        generalReviewAdapter = InstitutionDetailGeneralReviewsAdapter(generalReviews!!)
        rvCourseReview.adapter = generalReviewAdapter
        rvCourseReview.layoutManager = LinearLayoutManager(context)
    }

    override fun setupDetailedReviews(detailedReviews: List<DetailedReview>?) {
    }

    companion object {
        @JvmStatic
        fun newInstance(course: Course) =
                CourseReviewFragment().apply {
                    arguments = Bundle().apply {
                        putParcelable(COURSE_PARAM, course)
                    }
                }
    }
}
