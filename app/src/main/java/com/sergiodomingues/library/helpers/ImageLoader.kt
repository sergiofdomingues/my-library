package com.sergiodomingues.library.helpers

import android.widget.ImageView
import com.bumptech.glide.RequestManager
import com.sergiodomingues.library.R
import javax.inject.Inject

class ImageLoader @Inject constructor(private val requestManager: RequestManager) {

    fun loadPhotoCoverInto(photoPath: String?, destination: ImageView) {
        requestManager
            .load(photoPath)
            .error(R.drawable.ic_no_cover)
            .into(destination)
    }
} 