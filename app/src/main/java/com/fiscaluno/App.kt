package com.fiscaluno

import android.app.Application

import com.google.firebase.FirebaseApp

/**
 * Created by Wilder on 10/10/17.
 */

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}
