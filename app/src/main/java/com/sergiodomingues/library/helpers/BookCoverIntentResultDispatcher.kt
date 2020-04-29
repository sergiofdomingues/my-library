package com.sergiodomingues.library.helpers

import android.app.Activity
import android.net.Uri
import com.sergiodomingues.library.base.BaseActivity
import com.sergiodomingues.library.bookadd.ui.AddBookActivity.Companion.REQUEST_PHOTO_INTENT_CHOOSER
import com.sergiodomingues.library.helpers.BookCoverIntentResultDispatcher.Outcome.Canceled
import com.sergiodomingues.library.helpers.BookCoverIntentResultDispatcher.Outcome.PhotoTaken
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