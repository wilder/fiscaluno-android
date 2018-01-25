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
import com.fiscaluno.rating.RatingActivity
import java.util.ArrayList


/**
 * Created by Wilder on 16/07/17.
 */

class InstitutionListAdapter (mDataset: ArrayList<Institution>, ratingActivity: RatingActivity) : RecyclerView.Adapter<InstitutionListAdapter.ViewHolder>() {

    var mDataset: ArrayList<Institution> = mDataset
    val ratingActivity: RatingActivity = ratingActivity //TODO: Change to interface to decouple the code

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
            ratingActivity.saveInstitution(institution)
            ratingActivity.proceedListener()
        }
    }

    override fun getItemCount(): Int {
        return mDataset.size
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        // each data item is just a string in this case
        var institutionName: TextView = v.findViewById(R.id.institution_name_tv_item)
        var icon: ImageView = v.findViewById(R.id.icon_iv_item)
        var conainer: LinearLayout = v.findViewById(R.id.institution_name_ll_item)

    }

    fun swap(data: List<Institution>) {
        mDataset.clear()
        mDataset.addAll(data)
        notifyDataSetChanged()
    }
}


