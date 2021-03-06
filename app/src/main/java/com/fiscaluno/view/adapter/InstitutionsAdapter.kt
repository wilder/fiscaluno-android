package com.fiscaluno.view.adapter

import android.content.Context
import android.content.Intent
import android.view.View
import com.fiscaluno.model.Institution
import com.fiscaluno.view.institutionDetail.InstitutionDetailActivity
import java.util.ArrayList

class InstitutionsAdapter(override val rateableEntities: ArrayList<Institution>, context: Context) : RateableEntitiesAdapter(rateableEntities, context) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val institution = rateableEntities[position]

        holder.subRareableEntityName.visibility = View.GONE

        institution.let {
            holder.rateableEntityImage.setImageURI(it.imageUri)
            holder.rateableEntityName.text = it.name
            holder.rating.rating = it.averageRating
        }

        holder.rateableEntityCard.setOnClickListener {
            val intent = Intent(context, InstitutionDetailActivity::class.java)
            intent.putExtra("institution", institution)
            context.startActivity(intent)
        }
    }

}