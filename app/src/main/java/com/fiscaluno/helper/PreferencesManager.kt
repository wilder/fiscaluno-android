package com.fiscaluno.helper

import android.content.Context
import android.content.SharedPreferences
import com.fiscaluno.model.Student
import com.google.gson.Gson

/**
 * Manages dealing with SharedPreferences
 * Created by Wilder on 17/08/17.
 */
class PreferencesManager (context: Context) {
    val PREFS_FILENAME = "fiscaluno.prefs"
    val USER_INSTITUTION_ID = "user_institution_id"
    val USER = "user_obj"
    val TOKEN = "token"
    val INTRO_KEY = "intro_key"
    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0);

    var token: String?
        get() = prefs.getString(TOKEN, null)
        set(value) = prefs.edit().putString(TOKEN, value).apply()

    var userInstitutionId: String
        get() = prefs.getString(USER_INSTITUTION_ID, "")

        /**
         * This property is set after the user review a institution
         */
        set(value) = prefs.edit().putString(USER_INSTITUTION_ID, value).apply()

    var user: Student?
    //TODO: check if null is a problem
        get() = Gson().fromJson(prefs.getString(USER, ""), Student::class.java)
        set(user) = prefs.edit().putString(USER, Gson().toJson(user)).apply()

    var haveSeenIntro: Boolean
        get() = prefs.getBoolean(INTRO_KEY, false)
        set(value) = prefs.edit().putBoolean(INTRO_KEY, value).apply()

}