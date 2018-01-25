package com.fiscaluno.rating.detailedReview

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.fiscaluno.R
import android.view.MotionEvent
import android.widget.RatingBar
import com.fiscaluno.model.DetailedReview
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
        } else {
            holder.starsBar.rating = 0.0f
        }

        holder.starsBar.setIsIndicator(!clickable)
        if(clickable){
            holder.starsBar.setOnTouchListener { v, event ->
                if (event.action == MotionEvent.ACTION_UP) {
                    val touchPositionX = event.x
                    val width = holder.starsBar.getWidth()
                    val starsf = touchPositionX / width * 5.0f
                    val stars = starsf.toInt() + 1
                    holder.starsBar.rating = stars.toFloat()
                    review.rate = stars.toFloat()
                    v.isPressed = false
                }
                if (event.action == MotionEvent.ACTION_DOWN) {
                    v.isPressed = true
                }

                if (event.action == MotionEvent.ACTION_CANCEL) {
                    v.isPressed = false
                }
                true
            }
        }

    }

    override fun getItemCount(): Int {
        return mDataset.size
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var reviewName: TextView = v.findViewById(R.id.review_title_tv_dr_item)
        var starsBar: RatingBar = v.findViewById(R.id.rating_stars_dr_item)
    }

    fun getDetailedReviews(): ArrayList<DetailedReview> {
        return mDataset
    }

}


