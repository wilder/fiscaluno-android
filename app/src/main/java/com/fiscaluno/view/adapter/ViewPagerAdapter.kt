package com.fiscaluno.view.adapter

import java.util.ArrayList
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by Wilder on 13/07/17.
 */

class ViewPagerAdapter(manager: FragmentManager, private val mFragmentList: List<Fragment>, private val title: String) : FragmentPagerAdapter(manager) {

    override fun getItem(position: Int): Fragment {
        return mFragmentList[position]
    }

    override fun getCount(): Int {
        return mFragmentList.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return title
    }
}