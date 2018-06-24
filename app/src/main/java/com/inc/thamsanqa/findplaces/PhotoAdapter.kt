package com.inc.thamsanqa.findplaces

import android.content.Context
import android.graphics.Bitmap
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import com.inc.thamsanqa.findplaces.model.Photo
import com.squareup.picasso.Picasso
import android.graphics.drawable.Drawable
import com.squareup.picasso.Target
import java.util.ArrayList


class PhotoAdapter internal constructor(private val photos: List<Photo>, private val listener: OnImageSelectListener) : RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {

    private var context: Context? = null
    private var images: MutableList<Bitmap> = ArrayList()

    internal interface OnImageSelectListener {
        fun imageSelected(photo: Bitmap?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(context)
        val movieView = inflater.inflate(R.layout.photo_item, parent, false)
        return PhotoViewHolder(movieView)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(position)
        holder.imageView.setOnClickListener { listener.imageSelected(images[position]) }
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    inner class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var imageView: ImageView = itemView.findViewById(R.id.imageView)

        fun bind(position: Int) {

            val photo = photos[position]
            val imageUrl = buildImageURL(photo)

            val target = object : Target {
                override fun onPrepareLoad(placeHolderDrawable: Drawable?) {}

                override fun onBitmapFailed(errorDrawable: Drawable?) {}

                override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                    images.add(bitmap!!)
                }
            }

            Picasso.with(context)
                    .load(imageUrl)
                    .into(target)

            Picasso.with(context)
                    .load(imageUrl)
                    .fit()
                    .into(imageView)
        }

        private fun buildImageURL(photo: Photo): String {
            val baseUrl = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400"
            val builder = StringBuilder(baseUrl)
            builder.append(String.format("&photoreference=%s", photo.photoReference))
                    .append(String.format("&key=%s", "AIzaSyBnk9KVptIvESwmX7bTPHtwIK86MrZSOzY"))
            return builder.toString()
        }

    }
}
