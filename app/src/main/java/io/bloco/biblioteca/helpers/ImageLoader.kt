package io.bloco.biblioteca.helpers

import android.app.Activity
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import javax.inject.Inject

class ImageLoader @Inject constructor(private val requestManager: RequestManager) {
    fun loadImageInto(photoPath: String?, destination: ImageView) {
        requestManager.load(photoPath).into(destination)
    }
}
/*
class ImageLoader @Inject constructor(private val activity: Activity) {
    fun loadImageInto(photoPath: String?, destination: ImageView) {
        Glide.with(activity).load(photoPath).into(destination)
    }
}*/
