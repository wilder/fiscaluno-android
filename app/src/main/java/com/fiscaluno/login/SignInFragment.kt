package com.fiscaluno.login

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import com.facebook.CallbackManager
import com.fiscaluno.App
import com.fiscaluno.R
import com.fiscaluno.contracts.SignInContract
import com.fiscaluno.helper.PreferencesManager
import com.fiscaluno.model.Student
import com.fiscaluno.view.IntroActivity
import com.fiscaluno.view.MainActivity
import kotlinx.android.synthetic.main.activity_sign_in.*
import org.kodein.di.Kodein

class SignInFragment : DialogFragment(), SignInContract.View {

    lateinit var kodein: Kodein
    lateinit var presenter: SignInContract.Presenter
    lateinit var callbackManager: CallbackManager


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.activity_sign_in, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        kodein = (activity?.application as App).kodein
        presenter = SignInPresenter()
        presenter.bindView(this)
        callbackManager = CallbackManager.Factory.create()
        btn_sign_in.setOnClickListener { signInClick() }
    }

    override fun onResume() {
        super.onResume()
        val width = resources.displayMetrics.widthPixels
        val height = resources.displayMetrics.heightPixels
        dialog.window.setLayout(width-25, height-60)
    }

    private fun signInClick() {
        val name = et_name.text.toString()
        val lastname = et_lastname.text.toString()
        val email = et_email.text.toString()
        val password = et_password.text.toString()
        val passwordConfirmation = et_confirm_password.text.toString()
        signIn(name, lastname, email, password, passwordConfirmation)
    }

    fun signIn(name: String, lastname: String, email: String, password: String, passwordConfirmation: String) {
        if (validateUserInput(name, lastname, email, password, passwordConfirmation)) {
            presenter.signInWithEmail(name, lastname, email, password)
        }
    }

    fun validateUserInput(name: String, lastname: String, email: String, password: String, passwordConfirmation: String): Boolean {

        val nameIsValid = validateName(name)
        if (!nameIsValid) {
            tipName.error = "The name is invalid!"
        }

        val lastnameIsValid = validateName(lastname)
        if (!lastnameIsValid) {
            tipLastName.error = "The lastname is invalid!"
        }

        val passwordIsValid = validatePassword(password)
        if (!passwordIsValid) {
            tipPassword.error = "The password must be 8 characters long!"
        }

        val passwordsMatch = passwordsMatch(password, passwordConfirmation)
        if (!passwordsMatch) {
            tipPassword.error = "The password must match!"
            tipPasswordConfirmation.error = "The password must match!"
        }

        val emailIsValid = validateEmail(email)
        if (!emailIsValid) {
            tipEmail.error = "The email is invalid!"
        }

        return passwordIsValid && passwordsMatch && emailIsValid
    }

    private fun validateName(name: String?): Boolean {
        return !name.isNullOrBlank()
    }

    fun validateEmail(email: String) =
            true

    fun passwordsMatch(password: String, passwordConfirmation: String) =
            password == passwordConfirmation

    fun validatePassword(password: String): Boolean =
            password.length >= 8

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }

    override fun success(student: Student) {
        PreferencesManager(context!!).user = student
        if (PreferencesManager(context!!).haveSeenIntro) {
            startActivity(Intent(context!!, MainActivity::class.java))
        } else {
            startActivity(Intent(context!!, IntroActivity::class.java))
        }
        dismiss()
    }

    override fun siginCancelled() {

    }

    override fun siginError(message: String) {

    }

}
