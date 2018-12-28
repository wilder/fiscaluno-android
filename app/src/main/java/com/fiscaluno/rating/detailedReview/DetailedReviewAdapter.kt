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
import com.fiscaluno.login.LoginFragment
import com.fiscaluno.model.DetailedReview


/**
 * Created by Wilder on 16/07/17.
 */

class DetailedReviewAdapter constructor(val context: Context, override val dataSet: List<DetailedReview>,
                                        var clickable: Boolean, override val isUserLogged: Boolean) :
        LoggedInAwareAdapter<DetailedReview, DetailedReviewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_detailed_review, parent, false))
    }

    override fun setupFadedItem(holder: RecyclerView.ViewHolder, item: DetailedReview) {
        holder as DetailedReviewAdapter.ViewHolder
        holder.starsBar.setIsIndicator(true)
        holder.container.setBackgroundColor(context.resources.getColor(R.color.faded_item_color))
        holder.container.setOnClickListener {
            //TODO: show dialog
            context.startActivity(Intent(context, LoginFragment::class.java))
        }
    }

    override fun setupRegularItem(holder: RecyclerView.ViewHolder, item: DetailedReview) {
        holder as DetailedReviewAdapter.ViewHolder

        holder.reviewName.text = item.description

        if (item.rate != null) {
            holder.starsBar.rating = item.rate!!
        } else {
            holder.starsBar.rating = 0.0f
        }

        holder.starsBar.setIsIndicator(!clickable)
        if (clickable) {
            setOnTouchListener(holder, item)
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

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var reviewName: TextView = v.findViewById(R.id.review_title_tv_dr_item)
        var starsBar: RatingBar = v.findViewById(R.id.rating_stars_dr_item)
        var container: LinearLayout = v.findViewById(R.id.detailedReviewContainer)
    }

    fun getDetailedReviews(): List<DetailedReview> = dataSet

}


