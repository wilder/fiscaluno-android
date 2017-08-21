package com.fiscaluno.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.fiscaluno.R
import com.fiscaluno.helper.PreferencesManager
import com.fiscaluno.model.Student

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)

        val prefs = PreferencesManager(this)
        val student = prefs.user

        if (isValidUser(student)) {
            if (prefs.haveSeenIntro) {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            } else {
                startActivity(Intent(this@SplashActivity, IntroActivity::class.java))
            }
        } else {
            startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
        }
    }

    /**
     * TODO: Check if user is valid on database
     */
    private fun isValidUser(student: Student?) : Boolean{
        return student != null
    }
}
