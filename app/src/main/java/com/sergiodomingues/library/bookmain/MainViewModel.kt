package com.sergiodomingues.library.bookmain

import com.sergiodomingues.library.base.viewmodel.BaseViewModel
import com.sergiodomingues.library.helpers.FileManager
import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val fileManager: FileManager
) : BaseViewModel() {

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