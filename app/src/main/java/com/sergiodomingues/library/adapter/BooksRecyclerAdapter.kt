package com.sergiodomingues.library.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.sergiodomingues.library.R
import com.sergiodomingues.library.model.Book
import com.sergiodomingues.library.model.BookHolder

class BooksRecyclerAdapter(
    private val books: MutableList<Book>,
    private val interfaceRef: ListItemLongClick
) :
    RecyclerView.Adapter<BookHolder>() {

    interface ListItemLongClick {
        fun itemDelete(book: Book)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookHolder {
        val inflatedView = parent.inflate(R.layout.recyclerview_item_row, false)
        return BookHolder(inflatedView, interfaceRef)
    }

    override fun getItemCount(): Int = books.size

    override fun onBindViewHolder(holder: BookHolder, position: Int) {
        val itemBook = books[position]
        holder.bindBook(itemBook)
    }

    private fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
    }
}