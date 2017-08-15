package com.fiscaluno.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.fiscaluno.R
import com.fiscaluno.model.DetailedReview
import com.fiscaluno.model.Institution
import java.util.ArrayList

/**
 * Created by Wilder on 14/08/17.
 */
class TopInstitutionsAdapter constructor(institutions: ArrayList<Institution>) : RecyclerView.Adapter<com.fiscaluno.view.adapter.TopInstitutionsAdapter.ViewHolder>() {

    var institutions = institutions

    override fun getItemCount(): Int {
        return institutions.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val institution = institutions.get(position)
        holder.institutionName.text = institution.name
        holder.averageRating.text = institution.averageRating!!.toString()
        holder.rating.rating = institution.averageRating!!
        holder.ratedByCount.text = institution.reviewdBy.toString()
        //TODO: Set imagedrawable
        //holder.institutionImage.setImageDrawable()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder? {
        val layoutInflater = LayoutInflater.from(parent.context)
        return TopInstitutionsAdapter.ViewHolder(layoutInflater.inflate(R.layout.item_institution_main, parent, false))
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val institutionName: TextView = v.findViewById(R.id.institutionNameTv) as TextView
        val rating: RatingBar = v.findViewById(R.id.institutionRating) as RatingBar
        val institutionImage : ImageView = v.findViewById(R.id.institutionIv) as ImageView
        val ratedByCount: TextView = v.findViewById(R.id.ratedBy) as TextView
        val averageRating: TextView = v.findViewById(R.id.average) as TextView
    }

}