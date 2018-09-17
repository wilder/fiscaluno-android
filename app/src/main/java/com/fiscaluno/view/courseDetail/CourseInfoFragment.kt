package com.fiscaluno.view.courseDetail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat.getDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fiscaluno.R
import com.fiscaluno.model.Course
import kotlinx.android.synthetic.main.activity_course_info_fragment.*

private const val COURSE_PARAM = "courseParam"

class CourseInfoFragment : Fragment() {

    private lateinit var course: Course

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.activity_course_info_fragment, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            course = it.getParcelable(COURSE_PARAM)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        courseType.text = course.courseType

        loadDuration()
        loadMinAndMaxPrice(course.monthlyValueRange)
        loadCoursePeriod(course.coursePeriods[0])

    }

    fun loadDuration() {
        averageDuration.text = "4 a 6 anos"
    }

    fun loadMinAndMaxPrice(monthlyValueRange: List<Float>) {
        minPrice.text = if (monthlyValueRange[0] == 0.toFloat())
            "Desconhecido" else monthlyValueRange[0].toString()
        maxPrice.text = if (monthlyValueRange[1] == 0.toFloat())
            "Desconhecido" else monthlyValueRange[1].toString()
    }

    fun loadCoursePeriod(coursePeriod: String) {
        //TODO: handle multiple periods
        //availablePeriods.text = coursePeriods.joinToString(separator = ",")
        availablePeriods.text = coursePeriod

        if(coursePeriod.equals("nightly", ignoreCase = true)) {
            ivCoursePeriod.setImageDrawable(getDrawable(context!!, R.drawable.ic_night))
        } else {
            ivCoursePeriod.setImageDrawable(getDrawable(context!!, R.drawable.ic_sun))

        }
    }

    companion object {
        @JvmStatic
        fun newInstance(course: Course) =
                CourseInfoFragment().apply {
                    arguments = Bundle().apply {
                        putParcelable(COURSE_PARAM, course)
                    }
                }
    }

}
