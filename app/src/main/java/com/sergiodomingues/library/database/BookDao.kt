package com.sergiodomingues.library.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sergiodomingues.library.model.Book

@Dao
interface BookDao {
    @Query("SELECT * FROM Book WHERE id = :id")
    fun getBookById(id: Int): Book

    @Query("SELECT * FROM Book")
    fun getAllBooks(): List<Book>

    @Query("DELETE FROM Book WHERE id = :id")
    fun deleteBookById(id: Int) //DOES NOT WORK WELL

    @Query("DELETE FROM Book WHERE title = :title")
    fun deleteBookByTitle(title: String)

    @Query("SELECT count(*) FROM Book")
    fun countBooks(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertBook(newBook: Book)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMultipleBooks(books: List<Book>)

    @Query("DELETE FROM Book")
    fun deleteAllBooks()
}