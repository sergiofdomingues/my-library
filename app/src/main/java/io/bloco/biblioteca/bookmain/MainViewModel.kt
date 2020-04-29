package io.bloco.biblioteca.bookmain

import com.jakewharton.rxrelay2.BehaviorRelay
import io.bloco.biblioteca.helpers.FileManager
import io.bloco.biblioteca.base.viewmodel.BaseViewModel
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val fileManager: FileManager
): BaseViewModel() {

    private val bookCoverDeletion = BehaviorRelay.create<String>()

    init {
        bookCoverDeletion
            .flatMapSingle { fileManager.deletePhotoFile(it) }
            .subscribe()
            .addTo(disposables)
    }

    // Input

    fun bookDeletionClicked(filePath: String) = bookCoverDeletion.accept(filePath)

    // Output
}