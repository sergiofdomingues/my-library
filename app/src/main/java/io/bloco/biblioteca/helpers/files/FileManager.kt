package io.bloco.biblioteca.helpers.files

import android.content.Context
import android.net.Uri
import androidx.core.net.toFile
import androidx.core.net.toUri
import androidx.core.util.Preconditions
import timber.log.Timber
import java.io.*
import java.text.SimpleDateFormat
import java.util.*

class FileManager(val context: Context) {
    var currentPhotoPath: String? = null

    fun deletePhotoFile(bookPhotoPath: String?) {
        bookPhotoPath?.toUri()?.toFile()?.apply {
            if (exists()) delete()
        }
    }

    fun copyPhotoFileToAppStorage(photoUri: Uri) {

        var fileIn: FileInputStream?
        try {
            val parcelFileDes = context.contentResolver.openFileDescriptor(photoUri, "r")
            parcelFileDes.let {
                Preconditions.checkNotNull(parcelFileDes, "Could not open file URI")
                val fileDes = parcelFileDes.fileDescriptor
                fileIn = FileInputStream(fileDes)
            }
        } catch (e: FileNotFoundException) {
            Timber.e(e, "saveUriToFile")
            return
        }

        val destinationFile: File? = try {
            createImageFile()
        } catch (e: IOException) {
            Timber.e(e, "Error occurred while creating the File")
            null
        }

        var bInStream: BufferedInputStream? = null
        var bOutStream: BufferedOutputStream? = null

        try {
            bInStream = BufferedInputStream(fileIn)
            bOutStream = BufferedOutputStream(FileOutputStream(destinationFile, false))
            val buf = ByteArray(1024)
            while (bInStream.read(buf) != -1) {
                bOutStream.write(buf)
                bOutStream.flush()
            }

        } catch (e: IOException) {
            Timber.e(e, "Exception saving image file")
            e.printStackTrace()
        } finally {
            try {
                bInStream?.close()
                bOutStream?.close()
            } catch (e: IOException) {
                Timber.e(e, "Exception closing file streams")
            }
            currentPhotoPath = destinationFile?.path
        }
    }

    @Throws(IOException::class)
    fun createImageFile(): File {
        // Create an image file name
        val timeStamp: String =
            SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())

        return File.createTempFile(
            "JPEG_${timeStamp}_",
            ".jpg", context.filesDir
        ).apply {
            currentPhotoPath = absolutePath
        }
    }
}