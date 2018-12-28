package com.fiscaluno.repository

import android.app.Activity
import android.content.Context
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager

import com.fiscaluno.helper.PreferencesManager
import com.fiscaluno.login.LoginFragment

object AnonymousUserTracker {

    private val MAX_OFFLINE_ACCESS = 3

    /**
     * Tracks user usage in app to ask for login after a determined number of access
     * @param context
     */
    fun trackAccessIfNotLoggedIn(activity: Activity) {
        val preferencesManager = PreferencesManager(activity)
        if (!preferencesManager.isUserLoggedIn()) {
            trackAccess((activity as FragmentActivity).supportFragmentManager, preferencesManager)
        }
    }

    private fun trackAccess(fragmentManager: FragmentManager, preferencesManager: PreferencesManager) {
        val currentAccess = preferencesManager.detailsSeenCount + 1
        preferencesManager.detailsSeenCount = currentAccess

        if (currentAccess >= MAX_OFFLINE_ACCESS) {
            LoginFragment().showNow(fragmentManager, "dialog")
        }
    }

}
