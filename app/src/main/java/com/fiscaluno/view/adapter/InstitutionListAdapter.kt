package com.fiscaluno.view.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.fiscaluno.R
import com.fiscaluno.model.Institution

/**
 * Created by Wilder on 16/07/17.
 */

class InstitutionListAdapter(private val mDataset: List<Institution>) : RecyclerView.Adapter<InstitutionListAdapter.ViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, andØØ
    // you provide access to all the views for a data item in a view holder
    internal class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        // each data item is just a string in this case

        var institutionName: TextView
        var icon: ImageView

        init {
            institutionName = v.findViewById(R.id.institution_name_tv_item) as TextView
            icon = v.findViewById(R.id.icon_iv_item) as ImageView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): InstitutionListAdapter.ViewHolder {
        val context = parent.context
        // create a new view
        val v = LayoutInflater.from(context).inflate(R.layout.item_institution_name, parent, false)

        val vh = ViewHolder(v)

        return vh
    }


    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        val institution = mDataset[position]

        //TODO: Change to simpledraweeview
        holder.icon.setImageURI(institution.imageUri)


        // Set click listener for the whole post view
        holder.itemView.setOnClickListener {
            //TODO: Go to next Review pagge
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return mDataset.size
    }
}