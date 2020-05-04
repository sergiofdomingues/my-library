package com.sergiodomingues.library.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sergiodomingues.library.model.Book
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface BookDao {
    @Query("SELECT * FROM Book WHERE id = :id")
    fun getBookById(id: Int): Book

    @Query("SELECT * FROM Book")
    fun getAllBooks(): Single<List<Book>>

    @Query("DELETE FROM Book WHERE id = :id")
    fun deleteBookById(id: Long): Completable //DOES NOT WORK WELL

    @Query("DELETE FROM Book WHERE title = :title")
    fun deleteBookByTitle(title: String): Completable

    @Query("SELECT count(*) FROM Book")
    fun countBooks(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertBook(newBook: Book): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMultipleBooks(books: List<Book>)

    @Query("DELETE FROM Book")
    fun deleteAllBooks()
}