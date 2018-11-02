package com.fiscaluno.contracts

interface LoggedInAwareAdapter<out E> {
    
    val maxItems: Int
        get() = 4

    val isUserLogged: Boolean
    val dataSet: List<E>

    fun getComputedItemCount(): Int {
        var amountOfItemsUserCanSee = dataSet.size
        if(!isUserLogged && dataSet.size > maxItems) {
            amountOfItemsUserCanSee = maxItems
        }
        return amountOfItemsUserCanSee
    }

    fun shouldFadeCurrentItem(position: Int): Boolean =
            !isUserLogged && position == maxItems - 1

}