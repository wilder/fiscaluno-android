package com.fiscaluno.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.fiscaluno.R
import com.fiscaluno.helper.PreferencesManager

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)

        //TODO: change to local database
        if (PreferencesManager(this).haveSeenIntro) {
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
        } else {
            startActivity(Intent(this@SplashActivity, IntroActivity::class.java))
        }
    }

}
