package com.fiscaluno.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.fiscaluno.R
import com.fiscaluno.extensions.toUri
import com.fiscaluno.model.Institution
import android.support.v4.view.ViewPager
import android.widget.RatingBar
import com.fiscaluno.model.GeneralReview
import com.fiscaluno.view.RatingCourseInfoFragment
import java.util.ArrayList


/**
 * Created by Wilder on 16/07/17.
 */

class InstitutionDetailReviewsAdapter constructor(mDataset: ArrayList<GeneralReview>) : RecyclerView.Adapter<InstitutionDetailReviewsAdapter.ViewHolder>() {

    var mDataset: ArrayList<GeneralReview> = mDataset

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_review, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val review = mDataset[position]

        holder.prosTv.text = review.pros
        holder.consTv.text = review.cons
        holder.timeTv.text = review.createdAt?.time.toString() //TODO: Format time
        holder.starsBar.rating = review.rate!!

    }

    override fun getItemCount(): Int {
        return mDataset.size
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var prosTv: TextView = v.findViewById(R.id.pros_tv) as TextView
        var consTv: TextView = v.findViewById(R.id.cons_tv) as TextView
        var timeTv: TextView = v.findViewById(R.id.time_tv) as TextView
        var starsBar: RatingBar = v.findViewById(R.id.rating_item) as RatingBar
    }

    fun getGeneralReviews(): ArrayList<GeneralReview> {
        return mDataset
    }

}


