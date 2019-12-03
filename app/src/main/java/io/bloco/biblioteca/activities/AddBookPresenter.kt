package io.bloco.biblioteca.activities

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import io.bloco.biblioteca.database.BookRepository
import io.bloco.biblioteca.helpers.FileManager
import io.bloco.biblioteca.helpers.IntentManager
import io.bloco.biblioteca.helpers.IntentManager.Companion.REQUEST_SELECT_PHOTO
import io.bloco.biblioteca.helpers.IntentManager.Companion.REQUEST_TAKE_PHOTO
import io.bloco.biblioteca.helpers.Validation
import io.bloco.biblioteca.helpers.ValidationErrors
import io.bloco.biblioteca.model.Book
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import timber.log.Timber
import java.io.File
import java.io.IOException
import javax.inject.Inject

class AddBookPresenter @Inject constructor() {
    @Inject lateinit var bookRepository: BookRepository
    @Inject lateinit var fileManager: FileManager
    @Inject lateinit var intentManager: IntentManager
    @Inject lateinit var validation: Validation

    private var tempFile: File? = null
    private var currentPhotoPath: String? = null

    private lateinit var view: View

    fun start(view: View) {
        this.view = view
    }

    fun addBook(newBook: Book) {
        val errorList = validation.validateBook(newBook)
        if (errorList.isEmpty()) {
            view.bookSuccessfullyAdded(true)
            bookRepository.addBook(newBook) { returnToSearchActivity() }
        } else {
            view.bookSuccessfullyAdded(false)
            view.showLayoutErrors(errorList)
        }
    }

    private fun returnToSearchActivity() {
        view.bookSuccessfullyAdded(true)
        view.returnToSearchActivity(RESULT_OK, intentManager.getResultIntent())
    }

    fun uploadPic() {
        val intent = intentManager.getUploadPictureIntent()
        view.startUploadPic(intent, REQUEST_SELECT_PHOTO)
    }

    fun requestCapturedPhotoLoad() {
        currentPhotoPath = tempFile?.path
        currentPhotoPath?.let {
            view.loadImage(it)
        }
    }

    fun loadPicAsync(photoUri: Uri) {
        doAsync {
            tempFile?.let { fileManager.deletePhotoFile(it.path) }
            tempFile = fileManager.copyPhotoFileToAppStorage(photoUri)
            currentPhotoPath = tempFile?.path
            uiThread {
                currentPhotoPath?.let { path ->
                    view.loadImage(path)
                }
            }
        }
    }

    fun takePic() {
        takePicAsync { startTakePicIntent(it) }
    }

    private fun takePicAsync(onComplete: ((File) -> Unit)) {
        doAsync {
            try {
                tempFile?.let { fileManager.deletePhotoFile(it.path) }
                tempFile = fileManager.createImageFile().also { file ->
                    uiThread {
                        onComplete.invoke(file)
                    }
                }
            } catch (e: IOException) {
                Timber.e(e, "Error occurred while creating the File")
            }
        }
    }

    private fun startTakePicIntent(tempFile: File) {
        view.startTakePic(REQUEST_TAKE_PHOTO, tempFile)
    }

    fun getIntentUriAuthorization(photoURI: Uri): Intent {
        return intentManager.getTakePictureIntent(MediaStore.EXTRA_OUTPUT, photoURI)
    }

    fun getCurrentPhotoPath(): String? {
        return currentPhotoPath
    }

    fun preparePhotoPath(appDir: String): String?{
        currentPhotoPath?.let { path ->
            if (path.startsWith(appDir, true)) // Book manually added
                currentPhotoPath = Uri.fromFile(File(path)).toString()
        }
        return currentPhotoPath
    }

    fun setCurrentPhotoPath(path: String?) {
        currentPhotoPath = path
    }

    fun deletePhotoPhileIfNeeded() {
        doAsync { fileManager.deletePhotoFile(tempFile?.path) }
    }

    interface View {
        fun returnToSearchActivity(result: Int, intent: Intent)
        fun bookSuccessfullyAdded(state: Boolean)
        fun showLayoutErrors(errorList: List<ValidationErrors>)
        fun startTakePic(requestCode: Int, tempFile: File)
        fun startUploadPic(intent: Intent, requestCode: Int)
        fun loadImage(photoPath: String)
    }
}