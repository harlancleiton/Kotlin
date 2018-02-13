package br.com.harlan.avaliabus.factory

import br.com.harlan.avaliabus.model.BaseModel
import br.com.harlan.avaliabus.model.UserModel

class UserFactory : BaseFactory {

    private var name: String
    var years: Int = 0
    private var email: String
    private var password: String

    constructor(email: String, password: String) {
        this.name = ""
        this.email = email
        this.password = password
    }

    constructor(name: String, email: String, password: String) {
        this.name = name
        this.email = email
        this.password = password
    }

    constructor(name: String, email: String, password: String, years: Int) {
        this.name = name
        this.years = years
        this.email = email
        this.password = password
    }

    override fun getObject(): BaseModel {
        var userModel: UserModel = UserModel()
        userModel.name = name
        userModel.years = years
        userModel.email = email
        userModel.password = password
        userModel.classChild = "usuarios"
        return userModel
    }
}