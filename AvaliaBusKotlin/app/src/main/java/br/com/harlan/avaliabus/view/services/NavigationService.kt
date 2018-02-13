package br.com.harlan.avaliabus.view.services

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.MediaStore
import android.support.annotation.LayoutRes
import android.support.annotation.NonNull
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.content.FileProvider
import android.widget.Toast
import br.com.harlan.avaliabus.business.services.NavigationServiceable
import br.com.harlan.avaliabus.model.BaseModel
import br.com.harlan.avaliabus.model.ImageModel
import br.com.harlan.avaliabus.view.BaseFragment
import java.io.File
import java.io.Serializable

class NavigationService : NavigationServiceable {

    //region Variables and Constructor
    var activity: Activity
    lateinit var fragmentManager: FragmentManager
    private @LayoutRes
    var layout: Int = 0
    val REQUEST_IMAGE_CAPTURE: Int = 1
    val RESULT_OK: Int = -1
    val RESULT_CANCELED: Int = 0
    val RESULT_FIRST_USER = 1

    constructor(@NonNull activity: Activity) {
        this.activity = activity
    }

    constructor(@NonNull activity: Activity, @NonNull fragmentManager: FragmentManager) {
        this.activity = activity
        this.fragmentManager = fragmentManager
    }
    //endregion Variables and Constructor

    override fun navigateTo(cls: Class<*>) {
        if (activity != null) {
            activity.startActivity(Intent(activity, cls))
            finishActivity()
        }
    }

    override fun navigateTo(cls: Class<*>, closeActivity: Boolean) {
        if (activity != null) {
            activity.startActivity(Intent(activity, cls))
            checkFinishActivity(closeActivity)
        }
    }

    override fun navigateTo(cls: Class<*>, objectSerializable: Serializable, name: String, closeActivity: Boolean) {
        if (activity != null) {
            val intent: Intent = Intent(activity, cls)
            intent.putExtra(name, objectSerializable)
            activity.startActivity(intent)
            checkFinishActivity(closeActivity)
        }
    }

    override fun finishActivity() {
        activity.finish()
    }

    fun checkFinishActivity(closeActivity: Boolean) {
        if (closeActivity)
            finishActivity()
    }

    fun uploadFragmentHere(@LayoutRes layout: Int) {
        this.layout = layout
    }

    @SuppressLint("ResourceType")
    override fun loadFragment(@NonNull fragment: BaseFragment) {
        if (fragmentManager != null && layout != null)
            fragmentManager.beginTransaction().replace(layout, fragment).commit()
        else
            Toast.makeText(activity, "Erro ao mudar o contexto da aplicação", Toast.LENGTH_LONG).show()
    }

    fun openGallery() {
        val intent: Intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        activity.startActivityForResult(intent, 1)
    }

    fun dispatchTakePictureIntent(packageManager: PackageManager, file: File) {
        val intent: Intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (intent.resolveActivity(packageManager) != null) {
            var photoURI: Uri = FileProvider.getUriForFile(activity, "com.example.android.fileprovider", file)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
            activity.startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
        }
    }

    fun dispatchTakePictureIntent(packageManager: PackageManager, fileable: BaseModel.Fileable) {
        val intent: Intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (intent.resolveActivity(packageManager) != null) {
            var photoURI: Uri = FileProvider.getUriForFile(activity, "com.example.android.fileprovider", fileable.file)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
            activity.startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
        }
    }

    fun dispatchTakePictureIntent(fragment: Fragment, packageManager: PackageManager, fileable: BaseModel.Fileable){
        val intent: Intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (intent.resolveActivity(packageManager) != null) {
            var photoURI: Uri = FileProvider.getUriForFile(fragment.context, "com.example.android.fileprovider", fileable.file)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
            fragment.startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
        }
    }

    fun saveGallery(imageModel: ImageModel) {
        val intent: Intent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
        intent.setData(imageModel.uri)
        activity.sendBroadcast(intent)
    }

    fun saveGallery(fragment: Fragment, imageModel: ImageModel) {
        val intent: Intent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
        intent.setData(imageModel.uri)
        fragment.activity.sendBroadcast(intent)
    }
}