package br.com.harlan.avaliabus.model

import com.google.firebase.database.Exclude
import java.io.File

abstract class FileModel : BaseModel(), BaseModel.Fileable {

    override var file: File? = null
        @Exclude
        get() = field
    var name: String = ""
    abstract val format: String
    val fullName: String = ""
        get() {
            field = name + "." + format
            return field
        }
    var date: String = ""
    var hour: String = ""
}