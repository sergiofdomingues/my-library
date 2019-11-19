package io.bloco.biblioteca.helpers.imageloader

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

class ImageLoader(val context: Context) {

    fun loadImageInto(photoPath: String?, destination: ImageView) {
        Glide.with(context).load(photoPath).into(destination)
    }

}