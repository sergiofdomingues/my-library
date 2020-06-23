package com.sergiodomingues.library.helpers

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import androidx.core.content.FileProvider
import com.sergiodomingues.library.R
import java.io.File
import javax.inject.Inject

class IntentBuilder @Inject constructor() {
    fun getImageChooserIntent(context: Context, file: File): Intent? {
        val outputFileUri =
            FileProvider.getUriForFile(context, context.getString(R.string.fileprovider), file)

        val allIntents: MutableList<Intent> = ArrayList()
        val captureIntent = getTakePictureIntent(MediaStore.EXTRA_OUTPUT, outputFileUri)
            .addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
        allIntents.add(captureIntent)

        val galleryIntent = getUploadPictureIntent()
        val listGallery = context.packageManager.queryIntentActivities(galleryIntent, 0)
        for (res in listGallery) {
            val intent = Intent(galleryIntent)
            intent.component = ComponentName(res.activityInfo.packageName, res.activityInfo.name)
            intent.setPackage(res.activityInfo.packageName)
            allIntents.add(intent)
        }
        val chooserIntent =
            Intent.createChooser(Intent(), context.getString(R.string.load_photo_choose_option))
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, allIntents.toTypedArray())
        return chooserIntent
    }

    private fun getUploadPictureIntent(): Intent {
        val intentOpenGallery =
            Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intentOpenGallery.type = "image/*"
        intentOpenGallery.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        return intentOpenGallery
    }

    private fun getTakePictureIntent(extraToIntent: String, photoUri: Uri): Intent {
        return Intent(MediaStore.ACTION_IMAGE_CAPTURE).putExtra(extraToIntent, photoUri)
    }
}