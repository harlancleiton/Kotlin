package br.com.harlan.avaliabus.model

import android.net.Uri
import com.google.firebase.database.Exclude
import java.io.File
import java.io.Serializable

abstract class BaseModel : Serializable {

    var objectId: String = ""
    open var userObjectId: String = ""
    var classChild: String = ""
        @Exclude
        get() = field

    interface Fileable {
        var pathFile: File?
        var file: File?
        val uri: Uri
            get () = Uri.fromFile(file)
        val absolutePath: String
            get() = file!!.absolutePath
    }
}