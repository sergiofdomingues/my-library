package com.sergiodomingues.library.bookadd

import android.net.Uri
import com.jakewharton.rxrelay2.BehaviorRelay
import com.jakewharton.rxrelay2.PublishRelay
import com.sergiodomingues.library.base.viewmodel.BaseViewModel
import com.sergiodomingues.library.database.BookRepository
import com.sergiodomingues.library.helpers.BookCoverIntentResultDispatcher.Outcome
import com.sergiodomingues.library.helpers.BookValidator
import com.sergiodomingues.library.helpers.FileManager
import com.sergiodomingues.library.helpers.ValidationErrors
import com.sergiodomingues.library.model.Book
import com.sergiodomingues.library.model.FoundBook
import com.sergiodomingues.library.util.Operation
import io.reactivex.rxkotlin.Observables
import io.reactivex.rxkotlin.addTo
import java.io.File
import javax.inject.Inject

class AddBookViewModel @Inject constructor(
    private val fileManager: FileManager,
    private val bookRepository: BookRepository,
    private val bookValidator: BookValidator
) : BaseViewModel() {

    private val chosenBookProvidedByIntent = BehaviorRelay.create<FoundBook>()
    private val startChangingBookCover = PublishRelay.create<Unit>()
    private val startPickPhotoIntentChooser = PublishRelay.create<File>()
    private val coverPhotoIntentOutcome = BehaviorRelay.create<Outcome>()
    private val photoTaken = PublishRelay.create<Unit>()
    private val photoPicked = PublishRelay.create<Uri>()
    private val validBookSaveRequested = BehaviorRelay.create<Book>()
    private val saveBookClicked = BehaviorRelay.create<Book>()

    private val initBookDetailFields = PublishRelay.create<FoundBook>()
    private val coverPhotoFile = BehaviorRelay.create<File>()
    private val coverPhotoUri = BehaviorRelay.createDefault("")
    private val returnToSearch = BehaviorRelay.create<Unit>()
    private val showInputFieldErrors = BehaviorRelay.create<List<ValidationErrors>>()
    private val bookDiscarded = PublishRelay.create<Unit>()

    init {
        chosenBookProvidedByIntent
            .subscribe {
                coverPhotoUri.accept(it.imageCover)
                initBookDetailFields.accept(it)
            }
            .addTo(disposables)

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
                coverPhotoUri.accept(it.path)
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
                    coverPhotoUri.accept(it.result.path)
                }
            }
            .addTo(disposables)

        validBookSaveRequested
            .flatMapSingle { bookRepository.addBook(it) }
            .subscribe {
                returnToSearch.accept(Unit)
            }
            .addTo(disposables)

        Observables.combineLatest(
            saveBookClicked.hide(),
            coverPhotoUri.hide()
        )
            .subscribe {
                validateBook(it.first, it.second)
            }
            .addTo(disposables)

        /* Do not dispose this stream.
        *  If so, it will be unsubscribed from observers inside AddBookActivity's onDestroy before even doing its purpose.
        */
        bookDiscarded
            .flatMap { coverPhotoUri.take(1) }
            .flatMapSingle { fileManager.deletePhotoFile(it) }
            .subscribe()
    }

    // Input

    fun startChangingBookCover() = startChangingBookCover.accept(Unit)
    fun photoTaken() = photoTaken.accept(Unit)
    fun photoPicked(uri: Uri) = photoPicked.accept(uri)
    fun coverPhotoIntentOutcome(outcome: Outcome) = coverPhotoIntentOutcome.accept(outcome)
    fun chosenBookProvidedByIntent(book: FoundBook) = chosenBookProvidedByIntent.accept(book)
    fun saveBookClicked(book: Book) = saveBookClicked.accept(book)
    fun bookDiscarded() = bookDiscarded.accept(Unit)

    // Output
    fun startPickPhotoIntentChooser() = startPickPhotoIntentChooser.hide()
    fun changeCover() = coverPhotoUri.hide().filter { it.isNotEmpty() }
    fun returnToSearch() = returnToSearch.hide()
    fun initBookDetailFields() = initBookDetailFields.hide()
    fun showInputFieldErrors() = showInputFieldErrors.hide()

    // Helpers

    private fun validateBook(book: Book, bookCover: String) {
        book.imageCover = bookCover
        val errorList = bookValidator.validate(book)
        if (errorList.isEmpty()) {
            validBookSaveRequested.accept(book)
        } else {
            showInputFieldErrors.accept(errorList)
        }
    }

    private fun respondToIntentOutcomeResult(outcome: Outcome) {
        when (outcome) {
            is Outcome.PhotoTaken -> photoTaken()
            is Outcome.PhotoPicked -> photoPicked(outcome.uri)
        }
    }
}