package io.bloco.biblioteca.helpers

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import io.bloco.biblioteca.activities.AddBookActivity
import io.bloco.biblioteca.activities.BookInfoActivity
import io.bloco.biblioteca.activities.SearchBookActivity
import io.bloco.biblioteca.model.Book
import io.bloco.biblioteca.model.FoundBook
import javax.inject.Inject

class IntentManager @Inject constructor() {

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

    fun getIntentAddBookActivity(context: Context, book: FoundBook) =
        Intent(context, AddBookActivity::class.java).also {
            it.putExtra(CHOSEN_BOOK, book)
        }

    fun getIntentAddBookActivity(context: Context): Intent {
        return Intent(context, AddBookActivity::class.java)
    }

    fun getResultIntent() =
        Intent().also {
            it.putExtra(
                RESULT_NEW_BOOK,
                ADD_NEW_BOOK
            )
        }

    fun getIntentSearchBookActivity(context: Context): Intent {
        return Intent(context, SearchBookActivity::class.java)
    }

    fun getIntentBookInfoActivity(context: Context, book: Book) =
        Intent(context, BookInfoActivity::class.java).also {
            it.putExtra(BOOK_KEY, book)
        }

    companion object {
        const val ADD_NEW_BOOK = 10
        const val CHOSEN_BOOK = "ADDING_BOOK"
        private const val RESULT_NEW_BOOK = "ADDED_BOOK"
        private const val BOOK_KEY = "BOOK"
    }
}