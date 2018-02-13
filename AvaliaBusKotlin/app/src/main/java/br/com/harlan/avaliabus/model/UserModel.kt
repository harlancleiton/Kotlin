package br.com.harlan.avaliabus.model

import com.google.firebase.database.Exclude

class UserModel : BaseModel() {

    override var userObjectId: String = ""
        @Exclude
        get() = field
    var name: String = ""
    var years: Int = 0
    //lateinit var username: String
    var email: String = ""
    var password: String = ""
        @Exclude
        get() = field
}