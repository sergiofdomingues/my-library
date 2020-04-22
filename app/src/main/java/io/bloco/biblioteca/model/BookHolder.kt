package io.bloco.biblioteca.model

import android.app.AlertDialog
import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import io.bloco.biblioteca.R
import io.bloco.biblioteca.adapter.BooksRecyclerAdapter
import io.bloco.biblioteca.activities.BookInfoActivity
import kotlinx.android.synthetic.main.recyclerview_item_row.view.*

class BookHolder(v: View, private val interfaceRef: BooksRecyclerAdapter.ListItemLongClick)
    : RecyclerView.ViewHolder(v), View.OnClickListener,
    View.OnLongClickListener {
    private var view: View = v
    private var book: Book? = null
    private var dialog: AlertDialog? = null

    init {
        view.setOnClickListener(this)
        view.setOnLongClickListener(this)
    }

    override fun onClick(v: View?) {
        val context = itemView.context
        book?.let {
            val showBookIntent = BookInfoActivity.getIntent(context, it)
            context.startActivity(showBookIntent)
        }
    }

    override fun onLongClick(v: View?): Boolean {
        val context = itemView.context
        book?.let {
            openBookMenu(context, it)
            return true
        }
        return false
    }

    fun bindBook(book: Book) {
        this.book = book
        view.tvBookTitle.text = book.title
        view.tvBookAuthor.text = book.author
        when (book.read) {
            true -> view.tvBookRead.text = view.context.resources.getString(R.string.read)
            false -> view.tvBookRead.text = view.context.resources.getString(R.string.not_read)
        }
    }

    private fun openBookMenu(context: Context, book: Book) {
        dialog?.let {
            if (it.isShowing)
                it.dismiss()
        }
        dialog = null

        val builder = AlertDialog.Builder(context)
        builder.setItems(R.array.book_dialog_options) { _, which ->
            when (which) {
                0 -> interfaceRef.itemDelete(book)
                //1 -> share
            }
        }
        dialog = builder.create()
        dialog?.show()
    }
}