package br.com.harlan.avaliabus.view

import android.support.v7.widget.AppCompatButton
import android.support.v7.widget.AppCompatEditText
import android.support.v7.widget.AppCompatTextView
import br.com.harlan.avaliabus.R
import br.com.harlan.avaliabus.business.UserBusiness
import br.com.harlan.avaliabus.factory.UserFactory
import br.com.harlan.avaliabus.model.UserModel
import kotlinx.android.synthetic.main.activity_registration.*

class RegistrationActivity : BaseActivity(R.layout.activity_registration) {

    //region Variables
    private lateinit var edtName: AppCompatEditText
    private lateinit var edtEmail: AppCompatEditText
    private lateinit var edtPassword: AppCompatEditText
    private lateinit var edtRePassword: AppCompatEditText
    private lateinit var tvLogin: AppCompatTextView
    private lateinit var tvForgotPassword: AppCompatTextView
    private lateinit var btnRegistration: AppCompatButton
    //endregion Variables

    //region Functions
    override fun initializeComponents() {
        supportActionBar!!.hide()
        edtName = edt_nome_registro
        edtEmail = edt_email_registro
        edtPassword = edt_senha_registro
        edtRePassword = edt_repeti_senha_registro
        tvLogin = tv_login_registro
        tvForgotPassword = tv_esqueci_senha_registro
        btnRegistration = btn_registro
    }

    override fun addEvents() {
        tvLogin.setOnClickListener {
            navigationService.navigateTo(LoginActivity::class.java)
        }
        tvForgotPassword.setOnClickListener {
            navigationService.navigateTo(ForgotPasswordActivity::class.java)
        }
        btnRegistration.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser() {
        if (edtPassword.text.toString() == edtRePassword.text.toString()) {
            val userModel: UserModel = UserFactory(edtName.text.toString(), edtEmail.text.toString(), edtPassword.text.toString()).getObject() as UserModel
            UserBusiness(messageService, navigationService).registerUser(userModel)
        } else
            messageService.newToast("As senhas não conferem.")
    }
    //endregion Functions
}
