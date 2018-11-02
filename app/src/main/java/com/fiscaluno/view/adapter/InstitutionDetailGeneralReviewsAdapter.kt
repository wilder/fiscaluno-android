package com.fiscaluno.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import com.fiscaluno.R
import android.widget.RatingBar
import com.fiscaluno.contracts.LoggedInAwareAdapter
import com.fiscaluno.model.GeneralReview


/**
 * Created by Wilder on 16/07/17.
 */

class InstitutionDetailGeneralReviewsAdapter constructor(override val isUserLogged: Boolean, override val dataSet: List<GeneralReview>) :
        LoggedInAwareAdapter<GeneralReview, InstitutionDetailGeneralReviewsAdapter.ViewHolder>() {

    override val maxItems = 3

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_review, parent, false))
    }

    override fun setupFadedItem(holder: RecyclerView.ViewHolder, item: GeneralReview) {
        holder as InstitutionDetailGeneralReviewsAdapter.ViewHolder
        holder.fadeLayer.visibility = View.VISIBLE
        holder.starsBar.setIsIndicator(true)
    }

    override fun setupRegularItem(holder: RecyclerView.ViewHolder, item: GeneralReview) {
        holder as InstitutionDetailGeneralReviewsAdapter.ViewHolder
        holder.prosTv.text = item.pros
        holder.consTv.text = item.cons
        holder.timeTv.text = item.createdAt?.time.toString() //TODO: Format time
        holder.starsBar.rating = item.rate!!
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var prosTv: TextView = v.findViewById(R.id.pros_tv)
        var consTv: TextView = v.findViewById(R.id.cons_tv)
        var timeTv: TextView = v.findViewById(R.id.time_tv)
        var starsBar: RatingBar = v.findViewById(R.id.rating_item)
        var fadeLayer: FrameLayout = v.findViewById(R.id.fadeLayer)
    }

}


