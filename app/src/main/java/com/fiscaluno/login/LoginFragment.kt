package com.fiscaluno.login

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.fiscaluno.App
import com.fiscaluno.R
import com.fiscaluno.contracts.LoginContract
import com.fiscaluno.helper.PreferencesManager
import com.fiscaluno.model.Student
import com.fiscaluno.view.IntroActivity
import com.fiscaluno.view.MainActivity
import org.kodein.di.Kodein
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.view.Window
import kotlinx.android.synthetic.main.activity_login.*

class LoginFragment : DialogFragment(), LoginContract.View {

    lateinit var kodein: Kodein
    lateinit var presenter: LoginContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.activity_login, container, false)

    override fun onResume() {
        super.onResume()
        val width = resources.displayMetrics.widthPixels
        val height = resources.displayMetrics.heightPixels
        dialog.window.setLayout(width-25, height-60)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        kodein = (activity?.application as App).kodein

        presenter = LoginPresenter(kodein)
        presenter.bindView(this, activity!!)

        registerTv.setOnClickListener {
            this.dismiss()
            val transaction = fragmentManager?.beginTransaction()
            SignInFragment().show(transaction, "SignIfFragment")
        }

    }

    fun emailLogin(view: View?) {
        val email: String = et_email.text.toString()
        val pass: String = et_password.text.toString()
        presenter.loginWithEmail(email, pass)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        presenter.onActivityResult(requestCode, resultCode, data)
    }

    override fun successfulLogin(student: Student) {
        PreferencesManager(context!!).user = student
        if (PreferencesManager(context!!).haveSeenIntro) {
            startActivity(Intent(context!!, MainActivity::class.java))
        } else {
            startActivity(Intent(context!!, IntroActivity::class.java))
        }
        dismiss()
    }

    override fun loginCancelled() {
        Toast.makeText(context, "Login was cancelled by the user", Toast.LENGTH_SHORT)
                .show()
        dismiss()
    }

    override fun loginError(message: String) {
        Toast.makeText(context, "Error trying to login: $message", Toast.LENGTH_LONG)
                .show()
        dismiss()
    }

}
