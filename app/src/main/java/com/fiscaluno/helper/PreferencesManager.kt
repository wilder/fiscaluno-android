package com.fiscaluno.helper

import android.content.Context
import android.content.SharedPreferences

/**
 * Manages dealing with SharedPreferences
 * Created by Wilder on 17/08/17.
 */
class PreferencesManager (context: Context) {
    val PREFS_FILENAME = "fiscaluno.prefs"
    val USER_INSTITUTION_ID = "user_institution_id"
    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0);

    var userInstitutionId: String
        get() = prefs.getString(USER_INSTITUTION_ID, "")

        /**
         * This property is set after the user review a institution
         */
        set(value) = prefs.edit().putString(USER_INSTITUTION_ID, value).apply()
}