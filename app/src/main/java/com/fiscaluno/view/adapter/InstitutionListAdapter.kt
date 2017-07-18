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
import android.util.Log
import com.fiscaluno.view.RatingCourseInfoFragment
import java.util.ArrayList


/**
 * Created by Wilder on 16/07/17.
 */

class InstitutionListAdapter constructor(mDataset: ArrayList<Institution>, viewPager: ViewPager) : RecyclerView.Adapter<InstitutionListAdapter.ViewHolder>() {

    var mDataset: ArrayList<Institution> = mDataset
    val viewPager: ViewPager = viewPager

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_institution_name, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val institution = mDataset[position]
        //TODO: Change to simpledraweeview'
        if (institution.imageUri != null){
            holder.icon.setImageURI(institution.imageUri.toUri())
        }
        holder.institutionName.text = institution.name

        // Set click listener for the whole post view
        holder.conainer.setOnClickListener {
            (viewPager.adapter as ViewPagerAdapter).add(RatingCourseInfoFragment.newInstance(institution))
            viewPager.setCurrentItem(viewPager.currentItem + 1, true)
        }
    }

    override fun getItemCount(): Int {
        return mDataset.size
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        // each data item is just a string in this case
        var institutionName: TextView = v.findViewById(R.id.institution_name_tv_item) as TextView
        var icon: ImageView = v.findViewById(R.id.icon_iv_item) as ImageView
        var conainer: LinearLayout = v.findViewById(R.id.institution_name_ll_item) as LinearLayout

    }

    fun swap(data: List<Institution>) {
        mDataset.clear()
        mDataset.addAll(data)
        notifyDataSetChanged()
    }
}


