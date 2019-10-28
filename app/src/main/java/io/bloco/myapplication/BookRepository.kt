package io.bloco.myapplication

object BookRepository {

    private var memoryDataBase = ArrayList<Book>()   //outside class access blocked
    val dataBase: ArrayList<Book> get() = memoryDataBase  //outside class access granted

    init {
        val book1 =
            Book("Harry Potter e a Pedra Filosofal", "J. K. Rowling", "", "190-456-566", true)
        val book2 = Book("O livro do desassossego", "Fernando Pessoa", "", "191-456-566", false)
        val book3 = Book("War and Peace", "Leo Tolstoy", "", "192-456-566", false)
        val book4 = Book("Hamlet", "William Shakespeare", "", "193-456-566", false)

        memoryDataBase.apply {
            memoryDataBase.add(book1)
            memoryDataBase.add(book2)
            memoryDataBase.add(book3)
            memoryDataBase.add(book4)
        }
    }

    fun addBook(newBook: Book) {
        memoryDataBase.add(newBook)
    }

    fun deleteBook(book: Book) {
        memoryDataBase.remove(book)
    }
}