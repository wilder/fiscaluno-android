package com.fiscaluno.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.fiscaluno.R
import android.widget.RatingBar
import com.fiscaluno.extensions.format
import com.fiscaluno.model.GeneralReview
import java.util.ArrayList


/**
 * Created by Wilder on 16/07/17.
 */

class InstitutionDetailGeneralReviewsAdapter constructor(mDataset: List<GeneralReview>) : RecyclerView.Adapter<InstitutionDetailGeneralReviewsAdapter.ViewHolder>() {

    var mDataset: List<GeneralReview> = mDataset

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_review, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val review = mDataset[position]

        holder.prosTv.text = review.pros
        holder.consTv.text = review.cons
        holder.timeTv.text = review.createdAt?.format()
        holder.starsBar.rating = review.rate!!

    }

    override fun getItemCount(): Int {
        return mDataset.size
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var prosTv: TextView = v.findViewById(R.id.pros_tv)
        var consTv: TextView = v.findViewById(R.id.cons_tv)
        var timeTv: TextView = v.findViewById(R.id.time_tv)
        var starsBar: RatingBar = v.findViewById(R.id.rating_item)
    }

    fun getGeneralReviews(): List<GeneralReview> {
        return mDataset
    }

}


