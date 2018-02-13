package br.com.harlan.avaliabus.business

import br.com.harlan.avaliabus.business.services.MessageServiceable
import br.com.harlan.avaliabus.business.services.NavigationServiceable
import br.com.harlan.avaliabus.database.UserDatabase
import br.com.harlan.avaliabus.model.UserModel
import com.google.firebase.auth.FirebaseAuth

class UserBusiness : BaseBusiness {

    constructor(messageService: MessageServiceable, navigationService: NavigationServiceable)
            : super(messageService, navigationService)

    fun registerUser(userModel: UserModel) {
        if (validateRegistrationUser(userModel)) {
            userModel.classChild = USER_CHILD
            UserDatabase(taskService).registerUser(userModel)
        } else
            taskService.newError("Houve um erro ao validar os dados digitados")
    }

    fun validateLogin(userModel: UserModel) {
        if (validateLoginUser(userModel))
            UserDatabase(taskService).validateLogin(userModel)
        else
            taskService.newError("Houve um erro ao validar os dados digitados")
    }

    fun userLogOut() {
        if (isUserConnected())
            UserDatabase(taskService).userLogOut()
        else
            taskService.newError("Usuário não se encontra logado.")
    }

    fun redefinePassword(email: String) {
        UserDatabase(taskService).redefinePassword(email)
    }

    private fun validateLoginUser(userModel: UserModel): Boolean {
        if (!userModel.email.isEmpty() && !userModel.password.isEmpty())
            return true
        else
            return false
    }

    private fun validateRegistrationUser(userModel: UserModel): Boolean {
        if (!userModel.name.isEmpty() && !userModel.email.isEmpty() && !userModel.password.isEmpty())
            return true
        else
            return false
    }

    private fun isUserConnected(): Boolean {
        if (FirebaseAuth.getInstance().currentUser != null)
            return true
        else
            return false
    }
}