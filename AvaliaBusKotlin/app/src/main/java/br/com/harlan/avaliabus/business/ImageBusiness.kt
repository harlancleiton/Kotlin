package br.com.harlan.avaliabus.business

import android.net.Uri
import br.com.harlan.avaliabus.business.services.MessageServiceable
import br.com.harlan.avaliabus.business.services.NavigationServiceable
import br.com.harlan.avaliabus.business.services.TaskService
import br.com.harlan.avaliabus.database.FileDatabase
import br.com.harlan.avaliabus.factory.ImageFactory
import br.com.harlan.avaliabus.model.ImageModel
import br.com.harlan.avaliabus.singleton.FirebaseSingleton
import com.google.firebase.storage.StorageReference
import java.io.IOException

class ImageBusiness : BaseBusiness {

    constructor(messageService: MessageServiceable, navigationService: NavigationServiceable)
            : super(messageService, navigationService)

    constructor(taskService: TaskService) : super(taskService)

    fun newImage(): ImageModel {
        val imageModel: ImageModel = ImageFactory().getObject() as ImageModel
        val fileDatabase: FileDatabase = FileDatabase(taskService)
        try {
            imageModel.file = fileDatabase.createFile(imageModel, fileDatabase.CREATE_PHOTO)
        } catch (e: IOException) {
            taskService.newError("Algo deu errado ao tentar salvar o arquivo: " + e.message)
            e.printStackTrace()
        } catch (e: Exception) {
            taskService.newError("Algo deu errado ao tentar salvar o arquivo: " + e.message)
            e.printStackTrace()
        }
        return imageModel
    }

    fun sendImage(key: String, imageModelArrayList: ArrayList<ImageModel>, uploadType: Int) {
        val storageReferenceArrayList: ArrayList<StorageReference> = getStorageReference(key, imageModelArrayList, uploadType)
        val uriArrayList: ArrayList<Uri> = getUri(imageModelArrayList)
        if (!storageReferenceArrayList.isEmpty() && !uriArrayList.isEmpty())
            FileDatabase(taskService).sendFile(storageReferenceArrayList, uriArrayList, uploadType)
        else
            messageService.newToast("Ocorreu um erro ao tentar acessar as imagens.")
    }

    private fun getStorageReference(key: String, imageModelArrayList: ArrayList<ImageModel>, uploadType: Int): ArrayList<StorageReference> {
        val storageReferenceArrayList: ArrayList<StorageReference> = ArrayList()
        val storageReference = FirebaseSingleton.getStorageReference()
        if (uploadType == IMAGE_EVALUATION_UPLOAD_TYPE)
            for (imageModel in imageModelArrayList) {
                val imagemStorage = storageReference.child(IMAGES_STORAGE_CHILD + key + "/" + imageModel.name)
                storageReferenceArrayList.add(imagemStorage)
            }
        return storageReferenceArrayList
    }

    private fun getUri(imageModelArrayList: ArrayList<ImageModel>): ArrayList<Uri> {
        val uriArrayList: ArrayList<Uri> = ArrayList()
        for (imagemModel in imageModelArrayList) {
            uriArrayList.add(Uri.fromFile(imagemModel.file))
        }
        return uriArrayList
    }
}