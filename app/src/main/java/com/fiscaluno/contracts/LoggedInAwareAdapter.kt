package com.fiscaluno.contracts

import android.support.v7.widget.RecyclerView

abstract class LoggedInAwareAdapter<E, T : RecyclerView.ViewHolder> : RecyclerView.Adapter<T>() {

    val maxItems: Int
        get() = 4

    abstract val isUserLogged: Boolean
    abstract val dataSet: List<E>

    fun getComputedItemCount(): Int {
        var amountOfItemsUserCanSee = dataSet.size
        if(!isUserLogged && dataSet.size > maxItems) {
            amountOfItemsUserCanSee = maxItems
        }
        return amountOfItemsUserCanSee
    }

    fun shouldFadeCurrentItem(position: Int): Boolean =
            !isUserLogged && position == maxItems - 1

    override fun onBindViewHolder(holder: T, position: Int) {
        val item = dataSet[position]
        if (shouldFadeCurrentItem(position)) {
            setupFadedItem(holder, item)
        } else {
            setupRegularItem(holder, item)
        }
    }

    abstract fun setupFadedItem(holder: RecyclerView.ViewHolder, item: E)

    abstract fun setupRegularItem(holder: RecyclerView.ViewHolder, item: E)

    override fun getItemCount(): Int = getComputedItemCount()
}
