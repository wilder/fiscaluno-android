package com.fiscaluno.view.adapter

import android.content.Context
import android.view.View
import com.fiscaluno.model.Institution
import java.util.ArrayList

class TopInstitutionsAdapter(rateableEntities: ArrayList<Institution>, context: Context) : TopRateableEntitiesAdapter(rateableEntities, context) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val rateableEntity = rateableEntities[position]

        holder.subRareableEntityName.visibility = View.GONE

        rateableEntity.let {
            holder.rateableEntityName.text = it.name
            holder.averageRating.text = it.averageRating.toString()
            holder.rating.rating = it.averageRating
            holder.ratedByCount.text = it.reviewdBy.toString()
        }

        //TODO: Set imagedrawable
        //holder.rateableEntityImage.setImageDrawable()
        holder.institutionCard.setOnClickListener {
            // TODO: Go to rateableEntities tab on rateableEntity screen
        }
    }

}