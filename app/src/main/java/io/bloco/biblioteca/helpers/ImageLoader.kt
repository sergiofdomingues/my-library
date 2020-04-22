package io.bloco.biblioteca.helpers

import android.widget.ImageView
import com.bumptech.glide.RequestManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImageLoader @Inject constructor(private val requestManager: RequestManager) {
    fun loadImageInto(photoPath: String?, destination: ImageView) {
        requestManager.load(photoPath).into(destination)
    }
}