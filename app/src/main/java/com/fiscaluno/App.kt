package com.fiscaluno

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco

import com.google.firebase.FirebaseApp
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware

/**
 * Created by Wilder on 10/10/17.
 */

class App : Application(), KodeinAware {

    override val kodein: Kodein = Injector(this).dependency

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        Fresco.initialize(this)
    }
}
