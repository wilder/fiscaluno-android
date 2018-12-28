package com.fiscaluno.view.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.fiscaluno.model.Course
import android.view.LayoutInflater
import com.fiscaluno.R


/**
 * Created by Wilder on 16/07/17.
 */

class CourseSpinnerAdapter (val mDataset: List<Course>, context: Context,
                            val resource: Int) : ArrayAdapter<Course>(context, resource, 0, mDataset) {

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createItemView(position, convertView, parent);
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createItemView(position, convertView, parent);
    }

    private fun createItemView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = LayoutInflater.from(context).inflate(R.layout.rating_courses_adapter, parent, false)

        val label = view?.findViewById<TextView>(R.id.tvCourse)

        val item = mDataset[position]
        label?.text = item.name

        return view
    }

}


