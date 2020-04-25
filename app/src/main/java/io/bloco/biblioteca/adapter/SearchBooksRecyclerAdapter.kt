package io.bloco.biblioteca.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import io.bloco.biblioteca.R
import io.bloco.biblioteca.model.FoundBook
import kotlinx.android.synthetic.main.recyclerview_item_row.view.*

class SearchBooksRecyclerAdapter(
    private val books: MutableList<FoundBook>,
    val interfaceRef: ListItemClick
) :
    RecyclerView.Adapter<SearchBooksRecyclerAdapter.BookHolder>() {

    interface ListItemClick {
        fun itemClick(book: FoundBook)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookHolder {
        val inflatedView = parent.inflate(R.layout.sv_recyclerview_item_row, false)
        return BookHolder(inflatedView)
    }

    override fun getItemCount(): Int = books.size

    override fun onBindViewHolder(holder: BookHolder, position: Int) {
        val itemBook = books[position]
        holder.bindBook(itemBook)
    }

    inner class BookHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {

        private var view: View = v
        private var book: FoundBook? = null

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            book?.let { openSelectedBook(it) }
        }

        fun bindBook(book: FoundBook) {
            this.book = book
            view.tvBookTitle.text = book.title
            view.tvBookAuthor.text = book.author
        }

        private fun openSelectedBook(book: FoundBook) {
            interfaceRef.itemClick(book)
        }
    }

    private fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
    }
}