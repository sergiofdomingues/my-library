package io.bloco.biblioteca.helpers

import android.content.Context
import android.net.Uri
import android.os.ParcelFileDescriptor
import androidx.core.net.toFile
import androidx.core.util.Preconditions
import timber.log.Timber
import java.io.*
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class FileManager @Inject constructor(private val context: Context) {

    @Throws(IOException::class)
    fun createImageFile(): File {
        // Create an image file name
        val timeStamp: String =
            SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())

        return File.createTempFile(
            "JPEG_${timeStamp}_",
            ".jpg", context.filesDir
        )
    }

    fun deletePhotoFile(bookPhotoPath: String?) {
        bookPhotoPath?.let {
            val file = try {
                File(bookPhotoPath)
            } catch (e: IOException) {
                null
            }
            file?.let {
                if (it.exists())
                    it.delete()
            }
        }
    }

    fun deletePhotoFile(bookPhotoPath: Uri?) {
        bookPhotoPath?.let {
            val file = try {
                File(bookPhotoPath.toFile().path)
            } catch (e: IOException) {
                null
            }
            file?.let {
                if (it.exists())
                    it.delete()
            }
        }
    }

    fun copyPhotoFileToAppStorage(photoUri: Uri): File? {
        val fileIn: FileInputStream?
        val parcelFileDes: ParcelFileDescriptor?
        try {
            parcelFileDes = context.contentResolver.openFileDescriptor(photoUri, "r")
            Preconditions.checkNotNull(parcelFileDes, "Could not open file URI")
            val fileDes = parcelFileDes?.fileDescriptor
            if (fileDes != null) {
                fileIn = FileInputStream(fileDes)
            } else
                return null
        } catch (e: FileNotFoundException) {
            Timber.e(e, "saveUriToFile")
            return null
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
                fileIn.close()
                parcelFileDes.close()
            } catch (e: IOException) {
                Timber.e(e, "Exception closing file streams")
            }
            return destinationFile
        }
    }
}
