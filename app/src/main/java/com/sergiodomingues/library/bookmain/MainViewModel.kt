package com.sergiodomingues.library.bookmain

import com.jakewharton.rxrelay2.BehaviorRelay
import com.sergiodomingues.library.base.viewmodel.BaseViewModel
import com.sergiodomingues.library.database.BookRepository
import com.sergiodomingues.library.helpers.FileManager
import com.sergiodomingues.library.model.Book
import com.sergiodomingues.library.util.Operation
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val fileManager: FileManager,
    private val bookRepository: BookRepository
) : BaseViewModel() {

    private val bookDeletion = BehaviorRelay.create<Book>()
    private val getListOfBooks = BehaviorRelay.createDefault(Unit)
    private val deleteAllBooks = BehaviorRelay.create<Unit>()

    private val updateBooks = BehaviorRelay.create<List<Book>>()
    private val errors = BehaviorRelay.create<Error>()

    init {
        bookDeletion
            .map { it.imageCover }
            .filter { it.isNotEmpty() && !it.startsWith("http") }
            .flatMapSingle { fileManager.deletePhotoFile(it) }
            .subscribe()
            .addTo(disposables)

        bookDeletion
            .flatMapSingle { bookRepository.deleteBook(it.id) }
            .subscribe {
                when (it) {
                    is Operation.Success<Unit> -> {
                        getListOfBooks.accept(Unit)
                    }
                }
            }
            .addTo(disposables)

        getListOfBooks
            .flatMapSingle { bookRepository.getBooks() }
            .distinctUntilChanged()
            .subscribe {
                when (it) {
                    is Operation.Success<List<Book>> -> {
                        updateBooks.accept(it.result)
                    }
                    is Operation.Error<List<Book>> -> {
                        errors.accept(Error.CouldNotUpdateList)
                    }
                }
            }
            .addTo(disposables)

        deleteAllBooks
            .flatMapSingle {
                bookRepository.deleteAllBooks()
            }
            .subscribe {
                when (it) {
                    is Operation.Success<Unit> -> updateBooks.accept(emptyList())
                    is Operation.Error<Unit> -> errors.accept(Error.CouldNotDeleteBooks)
                }
            }
            .addTo(disposables)
    }

    // Input

    fun bookDeletionClicked(book: Book) = bookDeletion.accept(book)
    fun bookListChanged() = getListOfBooks.accept(Unit)
    fun deleteAllBooks() = deleteAllBooks.accept(Unit) // Todo: Delete all book cover's folder content when deleting all books

    // Output

    fun updateBooks() = updateBooks.hide()
    fun errors() = errors.hide()

    enum class Error { CouldNotUpdateList, CouldNotDeleteBooks }
}