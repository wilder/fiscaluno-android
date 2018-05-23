package com.fiscaluno.login

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.facebook.login.widget.LoginButton
import com.fiscaluno.App
import com.fiscaluno.R
import com.fiscaluno.contracts.LoginContract
import com.fiscaluno.data.FiscalunoApi
import com.fiscaluno.helper.PreferencesManager
import com.fiscaluno.model.Student
import com.fiscaluno.view.IntroActivity
import com.fiscaluno.view.MainActivity
import org.kodein.di.Kodein
import org.kodein.di.generic.instance


class LoginActivity : AppCompatActivity(), LoginContract.View {

    lateinit var kodein: Kodein
    lateinit var presenter: LoginContract.Presenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        kodein = (application as App).kodein

        presenter = LoginPresenter(kodein)
        presenter.bindView(this)
        presenter.prepareForLogin()

        val loginButton = findViewById<LoginButton>(R.id.login_button)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        presenter.onActivityResult(requestCode, resultCode, data)
    }

    override fun successfulLogin(student: Student) {
        //TODO: move to presenter
        PreferencesManager(this@LoginActivity).user = student

        if (PreferencesManager(this@LoginActivity).haveSeenIntro) {
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
        } else {
            startActivity(Intent(this@LoginActivity, IntroActivity::class.java))
        }

    }

    override fun loginCancelled() {
        Toast.makeText(baseContext, "Login was cancelled by the user", Toast.LENGTH_SHORT)
                .show()
    }

    override fun loginError(message: String) {
        Toast.makeText(baseContext, "Error trying to login: $message", Toast.LENGTH_LONG)
                .show()
    }

}
