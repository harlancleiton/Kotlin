package br.com.harlan.avaliabus.model

import android.os.Environment
import java.io.File

class ImageModel : FileModel() {

    override val format: String
        get() = ".jpg"
    override var pathFile: File? = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "AvaliaBus")
}