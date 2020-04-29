package io.bloco.biblioteca.helpers

import android.app.Activity
import android.net.Uri
import io.bloco.biblioteca.base.BaseActivity
import io.bloco.biblioteca.bookadd.ui.AddBookActivity.Companion.REQUEST_PHOTO_INTENT_CHOOSER
import io.bloco.biblioteca.helpers.BookCoverIntentResultDispatcher.Outcome.Canceled
import io.bloco.biblioteca.helpers.BookCoverIntentResultDispatcher.Outcome.PhotoTaken
import javax.inject.Inject

class BookCoverIntentResultDispatcher @Inject constructor() {
    fun dispatch(result: BaseActivity.ActivityResult): Outcome {
        if (result.resultCode != Activity.RESULT_OK) {
            return Canceled
        }

        when (result.requestCode) {
            REQUEST_PHOTO_INTENT_CHOOSER -> {
                if (result.data == null) {
                    return PhotoTaken
                }
                result.data.data?.let {
                    return Outcome.PhotoPicked(
                        it
                    )
                }
            }
        }
        return Canceled
    }

    sealed class Outcome {
        data class PhotoPicked(val uri: Uri) : Outcome()
        object PhotoTaken : Outcome()
        object Canceled : Outcome()
    }
}