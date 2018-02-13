package br.com.harlan.avaliabus.adapter

import android.content.Context
import android.graphics.BitmapFactory
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.harlan.avaliabus.R
import br.com.harlan.avaliabus.model.BaseModel

class ImageRecyclerAdapter : RecyclerView.Adapter<ImageRecyclerAdapter.ImageViewHolder> {

    private val fileableArrayList: ArrayList<BaseModel.Fileable>
    private val context: Context
    private val itemRemoved: ItemRemoved

    constructor(context: Context, fileableArrayList: ArrayList<BaseModel.Fileable>, itemRemoved: ItemRemoved) {
        this.context = context
        this.fileableArrayList = fileableArrayList
        this.itemRemoved = itemRemoved
    }

    override fun onBindViewHolder(holder: ImageViewHolder?, position: Int) {
        if (holder != null) {
            val targetW = context.resources.getDimension(R.dimen.photo_evaluation_height).toInt()
            val targetH = context.resources.getDimension(R.dimen.photo_evaluation_width).toInt()
            val bmOptions = BitmapFactory.Options()
            bmOptions.inJustDecodeBounds = true
            BitmapFactory.decodeFile(fileableArrayList.get(position).absolutePath, bmOptions)
            val photoW = bmOptions.outWidth
            val photoH = bmOptions.outHeight
            val scaleFactor = Math.min(photoW / targetW, photoH / targetH)
            bmOptions.inJustDecodeBounds = false
            bmOptions.inSampleSize = scaleFactor
            bmOptions.inPurgeable = true
            val bitmap = BitmapFactory.decodeFile(fileableArrayList.get(position).absolutePath, bmOptions)
            holder.imageView.setImageBitmap(bitmap)
            holder.floatingActionButton.setOnClickListener {
                fileableArrayList.removeAt(position)
                //fileableArrayList.removeAt(position)
                notifyDataSetChanged()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ImageViewHolder {
        val view: View = LayoutInflater.from(parent!!.context).inflate(R.layout.photo, parent, false)
        return ImageRecyclerAdapter.ImageViewHolder(view)
    }

    override fun getItemCount(): Int {
        return fileableArrayList.size
    }

    interface ItemRemoved {
        fun removed(fileable: BaseModel.Fileable)
    }

    class ImageViewHolder : RecyclerView.ViewHolder {

        var imageView: AppCompatImageView
        var floatingActionButton: FloatingActionButton

        constructor(itemView: View) : super(itemView) {
            imageView = itemView.findViewById(R.id.iv_foto_registrar)
            floatingActionButton = itemView.findViewById(R.id.fab_delete_image_card_view)
        }
    }
}