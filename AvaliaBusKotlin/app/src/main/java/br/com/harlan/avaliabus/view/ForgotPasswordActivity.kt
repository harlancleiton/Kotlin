package br.com.harlan.avaliabus.view

import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import br.com.harlan.avaliabus.R
import br.com.harlan.avaliabus.business.UserBusiness
import kotlinx.android.synthetic.main.activity_forgot_password.*

class ForgotPasswordActivity : BaseActivity(R.layout.activity_forgot_password) {

    private lateinit var edtEmail: EditText
    private lateinit var tvRegistration: TextView
    private lateinit var tvLogin: TextView
    private lateinit var btnRedefine: Button

    override fun initializeComponents() {
        supportActionBar!!.hide()
        edtEmail = edt_email_esqueci_senha
        tvRegistration = tv_registro_esqueci_senha
        tvLogin = tv_login_esqueci_senha
        btnRedefine = btn_redefinir_senha
    }

    override fun addEvents() {
        tvRegistration.setOnClickListener {
            navigationService.navigateTo(RegistrationActivity::class.java)
        }
        tvLogin.setOnClickListener {
            navigationService.navigateTo(LoginActivity::class.java)
        }
        btnRedefine.setOnClickListener {
            UserBusiness(messageService, navigationService).redefinePassword(edtEmail.text.toString())
        }
    }
}