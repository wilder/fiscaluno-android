package com.fiscaluno.view.adapter

import android.content.Context
import android.view.View
import com.fiscaluno.model.Course
import java.util.ArrayList

class CoursesAdapter(override val rateableEntities: ArrayList<Course>, override var context: Context) : RateableEntitiesAdapter(rateableEntities, context) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val rateableEntity = rateableEntities[position]

        rateableEntity.let {
            holder.rateableEntityImage.setImageURI(it.institution?.imageUri)
            holder.rateableEntityName.text = it.name

            if(it.institution?.name.isNullOrBlank()) {
                holder.subRareableEntityName.visibility = View.GONE
            } else {
                holder.subRareableEntityName.text = it.institution?.name
            }

            holder.averageRating.text = it.averageRating.toString()
            holder.rating.rating = it.averageRating
            holder.ratedByCount.text = it.ratedByCount.toString()
        }

        //TODO: Set imagedrawable
        //holder.rateableEntityImage.setImageDrawable()
        holder.institutionCard.setOnClickListener {
            // TODO: Go to courses tab on rateableEntity screen
        }
    }

}