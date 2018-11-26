package com.fiscaluno.view.adapter

import android.content.Context
import android.content.Intent
import android.view.View
import com.fiscaluno.model.Course
import com.fiscaluno.view.courseDetail.CourseDetailActivity
import java.util.ArrayList

class CoursesAdapter(override val rateableEntities: ArrayList<Course>, override var context: Context) : RateableEntitiesAdapter(rateableEntities, context) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val course = rateableEntities[position]

        course.let {
            holder.rateableEntityImage.setImageURI(it.institution?.imageUrl)
            holder.rateableEntityName.text = it.name

            if(it.institutionName.isNullOrBlank()) {
                holder.subRareableEntityName.visibility = View.GONE
            } else {
                holder.subRareableEntityName.text = it.institutionName
            }

            holder.rating.rating = it.averageRating
        }

        //TODO: Set imagedrawable
        //holder.rateableEntityImage.setImageDrawable()
        holder.rateableEntityCard.setOnClickListener {
            val intent = Intent(context, CourseDetailActivity::class.java)
            intent.putExtra("course", course)
            context.startActivity(intent)
        }
    }

}