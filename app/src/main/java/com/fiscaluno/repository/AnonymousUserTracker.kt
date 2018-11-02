package com.fiscaluno.repository

import android.content.Context
import android.content.Intent

import com.fiscaluno.helper.PreferencesManager
import com.fiscaluno.login.LoginActivity

object AnonymousUserTracker {

    private val MAX_OFFLINE_ACCESS = 3

    /**
     * Tracks user usage in app to ask for login after a determined number of access
     * @param context
     */
    fun trackAccessIfNotLoggedIn(context: Context) {
        val preferencesManager = PreferencesManager(context)
        if (!preferencesManager.isUserLoggedIn()) {
            trackAccess(context, preferencesManager)
        }
    }

    private fun trackAccess(context: Context, preferencesManager: PreferencesManager) {
        val currentAccess = preferencesManager.detailsSeenCount + 1
        preferencesManager.detailsSeenCount = currentAccess

        if (currentAccess >= MAX_OFFLINE_ACCESS) {
            //TODO: SHOW DIALOG
            context.startActivity(Intent(context, LoginActivity::class.java))
        }
    }

}
