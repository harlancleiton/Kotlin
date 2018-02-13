package br.com.harlan.avaliabus.factory

import br.com.harlan.avaliabus.model.BaseModel
import br.com.harlan.avaliabus.model.ImageModel
import br.com.harlan.avaliabus.singleton.FirebaseSingleton
import br.com.harlan.avaliabus.util.CurrentDateTime
import java.io.File

class ImageFactory : BaseFactory {

    private var file: File? = null

    constructor(file: File) : super() {
        this.file = file
    }

    constructor() : super()

    override fun getObject(): BaseModel {
        var imageModel: ImageModel = ImageModel()
        imageModel.userObjectId = FirebaseSingleton.getFirebaseAuth().uid!!
        imageModel.objectId = FirebaseSingleton.getDatabaseReference()!!.push().key
        imageModel.name = "AvaliaBus_" + imageModel.objectId
        if (file != null)
            imageModel.file = file
        imageModel.date = CurrentDateTime.currentDate
        imageModel.hour = CurrentDateTime.currentTime
        return imageModel
    }
}