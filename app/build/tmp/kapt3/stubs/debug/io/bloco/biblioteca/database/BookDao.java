package io.bloco.biblioteca.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import io.bloco.biblioteca.model.Book;

@androidx.room.Dao()
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\'J\b\u0010\u0004\u001a\u00020\u0005H\'J\u0010\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0003H\'J\u0010\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH\'J\u000e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\'J\u0010\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0007\u001a\u00020\u0003H\'J\u0010\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\rH\'J\u0016\u0010\u0011\u001a\u00020\u00052\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\r0\fH\'\u00a8\u0006\u0013"}, d2 = {"Lio/bloco/biblioteca/database/BookDao;", "", "countBooks", "", "deleteAllBooks", "", "deleteBookById", "id", "deleteBookByTitle", "title", "", "getAllBooks", "", "Lio/bloco/biblioteca/model/Book;", "getBookById", "insertBook", "newBook", "insertMultipleBooks", "books", "app_debug"})
public abstract interface BookDao {
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT * FROM Book WHERE id = :id")
    public abstract io.bloco.biblioteca.model.Book getBookById(int id);
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT * FROM Book")
    public abstract java.util.List<io.bloco.biblioteca.model.Book> getAllBooks();
    
    @androidx.room.Query(value = "DELETE FROM Book WHERE id = :id")
    public abstract void deleteBookById(int id);
    
    @androidx.room.Query(value = "DELETE FROM Book WHERE title = :title")
    public abstract void deleteBookByTitle(@org.jetbrains.annotations.NotNull()
    java.lang.String title);
    
    @androidx.room.Query(value = "SELECT count(*) FROM Book")
    public abstract int countBooks();
    
    @androidx.room.Insert(onConflict = androidx.room.OnConflictStrategy.IGNORE)
    public abstract void insertBook(@org.jetbrains.annotations.NotNull()
    io.bloco.biblioteca.model.Book newBook);
    
    @androidx.room.Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    public abstract void insertMultipleBooks(@org.jetbrains.annotations.NotNull()
    java.util.List<io.bloco.biblioteca.model.Book> books);
    
    @androidx.room.Query(value = "DELETE FROM Book")
    public abstract void deleteAllBooks();
}