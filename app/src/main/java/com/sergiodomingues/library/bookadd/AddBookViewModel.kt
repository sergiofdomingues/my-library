package com.sergiodomingues.library.bookadd

import android.net.Uri
import com.sergiodomingues.library.base.viewmodel.BaseViewModel
import com.sergiodomingues.library.helpers.BookCoverIntentResultDispatcher.Outcome
import com.sergiodomingues.library.helpers.FileManager
import com.sergiodomingues.library.util.Operation
import com.jakewharton.rxrelay2.BehaviorRelay
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.rxkotlin.addTo
import java.io.File
import javax.inject.Inject

class AddBookViewModel @Inject constructor(
    private val fileManager: FileManager
) : BaseViewModel() {

    private val startChangingBookCover = PublishRelay.create<Unit>()
    private val startPickPhotoIntentChooser = PublishRelay.create<File>()
    private val coverPhotoIntentOutcome = BehaviorRelay.create<Outcome>()
    private val photoTaken = PublishRelay.create<Unit>()
    private val photoPicked = PublishRelay.create<Uri>()
    private val bookCoverDeletion = BehaviorRelay.create<String>()

    private val coverPhotoFile = BehaviorRelay.create<File>()
    private val changeCover = BehaviorRelay.create<String>()

    init {
        startChangingBookCover
            .flatMapSingle { fileManager.createImageFile() }
            .subscribe {
                coverPhotoFile.accept(it)
                startPickPhotoIntentChooser.accept(it)
            }
            .addTo(disposables)

        coverPhotoIntentOutcome
            .subscribe {
                respondToIntentOutcomeResult(it)
            }
            .addTo(disposables)

        photoTaken
            .flatMap { coverPhotoFile.take(1) }
            .subscribe {
                changeCover.accept(it.path)
            }
            .addTo(disposables)

        photoPicked
            .flatMap { sourceUri ->
                coverPhotoFile
                    .take(1)
                    .flatMapSingle { destinationFile ->
                        fileManager.copyPhotoFileToAppStorage(sourceUri, destinationFile)
                    }
            }
            .subscribe {
                if (it is Operation.Success<File>) {
                    changeCover.accept(it.result.path)
                }
            }
            .addTo(disposables)

        bookCoverDeletion
            .flatMapSingle { fileManager.deletePhotoFile(it) }
            .subscribe()
            .addTo(disposables)
    }

    // Input

    fun startChangingBookCover() = startChangingBookCover.accept(Unit)
    fun photoTaken() = photoTaken.accept(Unit)
    fun photoPicked(uri: Uri) = photoPicked.accept(uri)
    fun addingBookCanceled(filePath: String) = bookCoverDeletion.accept(filePath)
    fun coverPhotoIntentOutcome(outcome: Outcome) = coverPhotoIntentOutcome.accept(outcome)

    // Output

    fun startPickPhotoIntentChooser() = startPickPhotoIntentChooser.hide()
    fun changeCover() = changeCover.hide()

    // Helpers

    private fun respondToIntentOutcomeResult(outcome: Outcome) {
        when (outcome) {
            is Outcome.PhotoTaken -> photoTaken()
            is Outcome.PhotoPicked -> photoPicked(outcome.uri)
        }
    }
}