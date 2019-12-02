package io.bloco.biblioteca.activities

import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import io.bloco.biblioteca.R
import io.bloco.biblioteca.api.ApiCaller
import io.bloco.biblioteca.helpers.IntentManager
import io.bloco.biblioteca.helpers.IntentManager.Companion.ADD_NEW_BOOK
import io.bloco.biblioteca.model.FoundBook
import timber.log.Timber
import javax.inject.Inject

class SearchBookPresenter @Inject constructor() {
    private lateinit var view: View
    @Inject lateinit var api: ApiCaller
    @Inject lateinit var intentManager: IntentManager

    fun start(view: View) {
        this.view = view
    }

    fun queryTextChanged(query: String) {
        if (api.queryIsAnIsbn(query))
            api.performSearchByIsbn(query) { refreshFoundBooksList(it) }
        else
            api.performSearchByQuery(query) { refreshFoundBooksList(it) }
    }

    fun itemClicked(context: Context, book: FoundBook) {
        val intent = intentManager.getIntentAddBookActivity(context, book)
        view.startAddBookActivityForResult(intent, ADD_NEW_BOOK)
    }

    fun addBookClicked(context: Context) {
        val intent = intentManager.getIntentAddBookActivity(context)
        view.startAddBookActivityForResult(intent, ADD_NEW_BOOK)
    }

    fun onActivityResultPerformed(resultCode: Int) {
        if (resultCode == RESULT_OK) {
            view.returnToMainActivity(intentManager.getResultIntent())
        } else if (resultCode == RESULT_CANCELED) {
            Timber.d("ResultCanceled")
        }
    }

    private fun refreshFoundBooksList(responseBooksList: (List<FoundBook>)?) {
        if (responseBooksList == null) {
            view.showError(R.string.connection_error)
        }
        responseBooksList?.let {
            view.updateFoundBooksList(it)
        }
    }

    interface View {
        fun showError(errorId: Int)
        fun updateFoundBooksList(newList: List<FoundBook>)
        fun returnToMainActivity(intent: Intent)
        fun startAddBookActivityForResult(intent: Intent, requestCode: Int)
    }
}