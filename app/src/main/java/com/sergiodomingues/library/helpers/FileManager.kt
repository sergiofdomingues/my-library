package com.sergiodomingues.library.helpers

import android.content.Context
import android.net.Uri
import android.os.ParcelFileDescriptor
import com.sergiodomingues.library.util.Operation
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.io.*
import javax.inject.Inject

class FileManager
@Inject constructor(private val context: Context) {

    @Throws(IOException::class)
    fun createImageFile() =
        Single.fromCallable {
            createImageFileBlocking()
        }.subscribeOn(Schedulers.io())


    @Throws(IOException::class)
    private fun createImageFileBlocking(): File {
        return File.createTempFile(
            "JPEG_",
            ".JPG",
            context.filesDir
        )
    }

    fun copyPhotoFileToAppStorage(photoUri: Uri, destinationFile: File): Single<Operation<File>> {
        return Single.fromCallable<Operation<File>> {
            val fileIn: FileInputStream?
            val parcelFileDes: ParcelFileDescriptor?
            try {
                parcelFileDes = context.contentResolver.openFileDescriptor(photoUri, "r")
                    ?: throw IllegalStateException("Could not open file URI.")

                val fileDes = parcelFileDes.fileDescriptor
                if (fileDes != null) {
                    fileIn = FileInputStream(fileDes)
                } else
                    return@fromCallable Operation.Error(Exception())
            } catch (e: FileNotFoundException) {
                Timber.e(e, "saveUriToFile")
                return@fromCallable Operation.Error(e)
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
                return@fromCallable Operation.Error(e)
            } finally {
                try {
                    bInStream?.close()
                    bOutStream?.close()
                    fileIn.close()
                    parcelFileDes.close()
                } catch (e: IOException) {
                    Timber.e(e, "Exception closing file streams")
                    return@fromCallable Operation.Error(e)
                }
            }
            return@fromCallable Operation.Success(destinationFile)
        }.subscribeOn(Schedulers.io())
    }

    fun deletePhotoFile(bookPhotoPath: String?): Single<Operation<Unit>> {
        return Single.fromCallable<Operation<Unit>> {
            bookPhotoPath?.let {
                val file = File(it)
                if (file.exists())
                    file.delete()
                return@fromCallable Operation.Success(Unit)
            }
        }.subscribeOn(Schedulers.io())
    }
}