package com.fiscaluno.view.adapter

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.fiscaluno.R
import com.fiscaluno.model.Course
import java.util.ArrayList

/**
 * Created by Wilder on 14/08/17.
 * TODO: generalize this and TopCoursesAdapter
 */
class TopCoursesAdapter constructor(courses: ArrayList<Course>, context: Context) : RecyclerView.Adapter<com.fiscaluno.view.adapter.TopCoursesAdapter.ViewHolder>() {

    var courses = courses
    var context = context

    override fun getItemCount(): Int = courses.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val course = courses[position]

        course.let {
            holder.institutionName.text = it.institution?.name
            holder.courseName.text = it.name
            holder.averageRating.text = it.averageRating?.toString()
            holder.rating.rating = it.averageRating ?: 0f
            holder.ratedByCount.text = it.reviewdBy.toString()
        }

        //TODO: Set imagedrawable
        //holder.institutionImage.setImageDrawable()
        holder.institutionCard.setOnClickListener {
            // TODO: Go to courses tab on course screen
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return TopCoursesAdapter.ViewHolder(layoutInflater.inflate(R.layout.item_course_main, parent, false))
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val institutionName: TextView = v.findViewById(R.id.institutionNameTv)
        val courseName: TextView = v.findViewById(R.id.courseNameTv)
        val rating: RatingBar = v.findViewById(R.id.courseRating)
        val institutionImage : ImageView = v.findViewById(R.id.institutionIv)
        val ratedByCount: TextView = v.findViewById(R.id.ratedBy)
        val averageRating: TextView = v.findViewById(R.id.average)
        val institutionCard: CardView = v.findViewById(R.id.courseCard)
    }

}