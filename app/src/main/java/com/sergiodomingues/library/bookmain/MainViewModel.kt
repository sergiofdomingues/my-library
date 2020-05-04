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

    private val updateBooks = BehaviorRelay.create<List<Book>>()

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
                }
            }
            .addTo(disposables)
    }

    // Input

    fun bookDeletionClicked(book: Book) = bookDeletion.accept(book)
    fun bookListChanged() = getListOfBooks.accept(Unit)

    // Output

    fun updateBooks() = updateBooks.hide()
}