package io.bloco.biblioteca

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BookDao {
    @Query("SELECT * FROM Book WHERE id = :id")
    fun getBookById(id: Int) :Book

    @Query("SELECT * FROM Book")
    fun getAllBooks() : List<Book>

    @Query("DELETE FROM Book WHERE id = :id")
    fun deleteBookById(id: Int)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertBook(newBook: Book)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMultipleBooks(books: List<Book>)
}