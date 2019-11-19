package io.bloco.biblioteca

import android.app.AlertDialog
import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recyclerview_item_row.view.*

class RecyclerAdapter(private val books: MutableList<Book>, val interfaceRef: ListItemLongClick) :
    RecyclerView.Adapter<RecyclerAdapter.BookHolder>() {

    interface ListItemLongClick {
        fun itemDelete(book: Book)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookHolder {
        val inflatedView = parent.inflate(R.layout.recyclerview_item_row, false)
        return BookHolder(inflatedView)
    }

    override fun getItemCount(): Int = books.size

    override fun onBindViewHolder(holder: BookHolder, position: Int) {
        val itemBook = books[position]
        holder.bindBook(itemBook)
    }

    inner class BookHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener,
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


}