package io.bloco.biblioteca.helpers

import android.content.Intent
import android.net.Uri
import android.provider.MediaStore

class IntentManager {

    //passar para outra class
    fun getUploadPictureIntent(): Intent {
        // Create intent to select photo from the gallery
        val intentOpenGallery =
            Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        //permissions
        intentOpenGallery.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        return intentOpenGallery
    }

    fun getTakePictureIntent(extraToIntent: String, photoUri: Uri): Intent {
        return Intent(MediaStore.ACTION_IMAGE_CAPTURE).putExtra(extraToIntent, photoUri)
    }
}