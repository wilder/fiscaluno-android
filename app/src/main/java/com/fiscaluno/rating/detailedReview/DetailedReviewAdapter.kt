package com.fiscaluno.rating.detailedReview

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.fiscaluno.R
import android.view.MotionEvent
import android.widget.LinearLayout
import android.widget.RatingBar
import com.fiscaluno.contracts.LoggedInAwareAdapter
import com.fiscaluno.login.LoginActivity
import com.fiscaluno.model.DetailedReview


/**
 * Created by Wilder on 16/07/17.
 */

class DetailedReviewAdapter constructor(val context: Context, override val dataSet: List<DetailedReview>,
                                        var clickable: Boolean, override val isUserLogged: Boolean) :
        RecyclerView.Adapter<DetailedReviewAdapter.ViewHolder>(), LoggedInAwareAdapter<DetailedReview> {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_detailed_review, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val review = dataSet[position]
        if (shouldFadeCurrentItem(position)) {
            holder.starsBar.setIsIndicator(true)
            holder.container.setBackgroundColor(context.resources.getColor(R.color.faded_item_color))
            holder.container.setOnClickListener {
                context.startActivity(Intent(context, LoginActivity::class.java))
            }
        } else {
            setupRegularItem(holder, review)
        }

    }

    private fun setupRegularItem(holder: ViewHolder, review: DetailedReview) {
        holder.reviewName.text = review.type

        if (review.rate != null) {
            holder.starsBar.rating = review.rate!!
        } else {
            holder.starsBar.rating = 0.0f
        }

        holder.starsBar.setIsIndicator(!clickable)
        if (clickable) {
            setOnTouchListener(holder, review)
        }
    }

    private fun setOnTouchListener(holder: ViewHolder, review: DetailedReview) {
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

    override fun getItemCount(): Int = getComputedItemCount()

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var reviewName: TextView = v.findViewById(R.id.review_title_tv_dr_item)
        var starsBar: RatingBar = v.findViewById(R.id.rating_stars_dr_item)
        var container: LinearLayout = v.findViewById(R.id.detailedReviewContainer)
    }

    fun getDetailedReviews(): List<DetailedReview> = dataSet

}


