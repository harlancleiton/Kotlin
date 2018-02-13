package br.com.harlan.avaliabus.database

import android.net.Uri
import br.com.harlan.avaliabus.database.services.TaskServiceable
import br.com.harlan.avaliabus.model.FileModel
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import java.io.File
import java.io.IOException

class FileDatabase : BaseDatabase {

    val CREATE_PHOTO: Int = 1

    constructor(taskService: TaskServiceable) : super(taskService)

    @Throws(IOException::class)
    fun createFile(fileModel: FileModel, action: Int): File? {
        /*var path: File? = null
        if (action == CREATE_PHOTO) {
            path = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "AvaliaBus")
        }
        if (!path!!.exists())
            path.mkdir()
        fileModel.name = "AvaliaBus_" + fileModel.objectId
        return File(path.toString() + File.separator + fileModel.name + fileModel.format)*/
        if (!fileModel.pathFile!!.exists())
            fileModel.pathFile!!.mkdir()
        return File(fileModel.pathFile!!.toString() + File.separator + fileModel.name + fileModel.format)
    }

    fun sendFile(storageReferenceArrayList: ArrayList<StorageReference>, uriArrayList: ArrayList<Uri>, uploadType: Int) {
        taskService.newUploadFiles(storageReferenceArrayList.size, uploadType)
        var uploadTask: UploadTask
        for (index in 0..storageReferenceArrayList.size - 1) {
            uploadTask = storageReferenceArrayList.get(index).putFile(uriArrayList.get(index))
            uploadTask.addOnProgressListener { taskSnapshot ->
                taskService.sendingFiles(taskSnapshot!!)
            }
            uploadTask.addOnCompleteListener { taskSnapshot ->
                taskService.uploadComplete(taskSnapshot!!)
            }
            uploadTask.addOnFailureListener { e ->
                taskService.fileUploadFailed(e!!)
            }
        }
    }
}