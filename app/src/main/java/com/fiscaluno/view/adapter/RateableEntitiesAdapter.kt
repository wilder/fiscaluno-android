package com.fiscaluno.view.adapter

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView
import com.fiscaluno.R
import com.fiscaluno.model.RateableEntity
import java.util.ArrayList

/**
 * Created by Wilder on 14/08/17.
 */
abstract class RateableEntitiesAdapter (open val rateableEntities: ArrayList<out RateableEntity>, open var context: Context) : RecyclerView.Adapter<com.fiscaluno.view.adapter.RateableEntitiesAdapter.ViewHolder>() {

    override fun getItemCount(): Int = rateableEntities.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return RateableEntitiesAdapter.ViewHolder(layoutInflater.inflate(R.layout.item_rateable_main, parent, false))
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val subRareableEntityName: TextView = v.findViewById(R.id.subRareableEntityNameTv)
        val rateableEntityName: TextView = v.findViewById(R.id.rateableEntityNameTv)
        val rating: RatingBar = v.findViewById(R.id.courseRating)
        val rateableEntityImage : SimpleDraweeView = v.findViewById(R.id.rateableEntityIv)
        val rateableEntityCard: CardView = v.findViewById(R.id.courseCard)
    }

}