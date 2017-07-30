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
import com.fiscaluno.model.DetailedReview
import com.fiscaluno.view.RatingCourseInfoFragment
import java.util.ArrayList


/**
 * Created by Wilder on 16/07/17.
 */

class DetailedReviewAdapter constructor(mDataset: ArrayList<DetailedReview>, clickable: Boolean) : RecyclerView.Adapter<DetailedReviewAdapter.ViewHolder>() {

    var mDataset: ArrayList<DetailedReview> = mDataset
    var clickable: Boolean = clickable

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_detailed_review, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val review = mDataset[position]

        holder.reviewName.text = review.type

        if(review.rate != null) {
            holder.starsBar.rating = review.rate!!
        }

        holder.starsBar.setIsIndicator(!clickable)
        if(clickable){
            holder.starsBar.setOnClickListener {
                review.rate = holder.starsBar.rating
            }
        }

    }

    override fun getItemCount(): Int {
        return mDataset.size
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var reviewName: TextView = v.findViewById(R.id.review_title_tv_dr_item) as TextView
        var starsBar: RatingBar = v.findViewById(R.id.rating_stars_dr_item) as RatingBar
    }

    fun getDetailedReviews(): ArrayList<DetailedReview> {
        return mDataset
    }

}


