package br.com.harlan.avaliabus.view

import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import br.com.harlan.avaliabus.R
import br.com.harlan.avaliabus.business.UserBusiness
import br.com.harlan.avaliabus.factory.UserFactory
import br.com.harlan.avaliabus.model.UserModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity {

    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var tvRegistrion: TextView
    private lateinit var tvForgotPassword: TextView
    private lateinit var btnLogin: Button

    constructor() : super(R.layout.activity_login)

    override fun initializeComponents() {
        supportActionBar!!.hide()
        edtEmail = edt_email_login
        edtPassword = edt_senha_login
        tvRegistrion = tv_registrar_login
        tvForgotPassword = tv_esqueci_senha_login
        btnLogin = btn_login
    }

    override fun addEvents() {
        btnLogin.setOnClickListener {
            newLogin()
        }
        tvRegistrion.setOnClickListener {
            navigationService.navigateTo(RegistrationActivity::class.java)
        }
        tvForgotPassword.setOnClickListener {
            navigationService.navigateTo(ForgotPasswordActivity::class.java)
        }
    }

    fun newLogin() {
        val userModel: UserModel = UserFactory(edtEmail.text.toString(), edtPassword.text.toString()).getObject() as UserModel
        UserBusiness(messageService, navigationService).validateLogin(userModel)
    }
}