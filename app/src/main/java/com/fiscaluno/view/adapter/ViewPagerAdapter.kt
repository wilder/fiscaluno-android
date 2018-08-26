package com.fiscaluno.view.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by Wilder on 13/07/17.
 */

class ViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {

    private val fragmentList: ArrayList<Fragment> = arrayListOf()
    private val fragmentTitleList: ArrayList<String> = arrayListOf()

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return fragmentTitleList[position]
    }

    fun add(fragment: Fragment, title: String){
        fragmentList.add(fragment)
        fragmentTitleList.add(title)
        notifyDataSetChanged()
    }


}