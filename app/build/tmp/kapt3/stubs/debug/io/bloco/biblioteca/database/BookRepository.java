package io.bloco.biblioteca.database;

import androidx.annotation.VisibleForTesting;
import io.bloco.biblioteca.model.Book;
import timber.log.Timber;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J \u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0010\b\u0002\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\nJ\u0018\u0010\u000b\u001a\u00020\u00062\u0010\b\u0002\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\nJ\u0006\u0010\f\u001a\u00020\u0006J \u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\b2\u0010\b\u0002\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\nJ\u0010\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\bH\u0007J \u0010\u0010\u001a\u00020\u00062\u0018\u0010\t\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0012\u0012\u0004\u0012\u00020\u00060\u0011J\u001e\u0010\u0013\u001a\u00020\u00062\u0016\b\u0002\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0011J\b\u0010\u0015\u001a\u00020\u0014H\u0007J\u0006\u0010\u0016\u001a\u00020\u0006R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Lio/bloco/biblioteca/database/BookRepository;", "", "bookDao", "Lio/bloco/biblioteca/database/BookDao;", "(Lio/bloco/biblioteca/database/BookDao;)V", "addBook", "", "newBook", "Lio/bloco/biblioteca/model/Book;", "onComplete", "Lkotlin/Function0;", "deleteAllBooks", "deleteAllBooksInDb", "deleteBook", "book", "deleteBookTesting", "getBooks", "Lkotlin/Function1;", "", "getCountBooks", "", "getNumBooks", "initBooksInDb", "app_debug"})
public final class BookRepository {
    private final io.bloco.biblioteca.database.BookDao bookDao = null;
    
    public final void getBooks(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.util.List<io.bloco.biblioteca.model.Book>, kotlin.Unit> onComplete) {
    }
    
    public final void getCountBooks(@org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> onComplete) {
    }
    
    public final void addBook(@org.jetbrains.annotations.NotNull()
    io.bloco.biblioteca.model.Book newBook, @org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function0<kotlin.Unit> onComplete) {
    }
    
    public final void deleteBook(@org.jetbrains.annotations.NotNull()
    io.bloco.biblioteca.model.Book book, @org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function0<kotlin.Unit> onComplete) {
    }
    
    public final void deleteAllBooks(@org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function0<kotlin.Unit> onComplete) {
    }
    
    @androidx.annotation.VisibleForTesting()
    public final void deleteBookTesting(@org.jetbrains.annotations.NotNull()
    io.bloco.biblioteca.model.Book book) {
    }
    
    @androidx.annotation.VisibleForTesting()
    public final int getNumBooks() {
        return 0;
    }
    
    public final void deleteAllBooksInDb() {
    }
    
    public final void initBooksInDb() {
    }
    
    public BookRepository(@org.jetbrains.annotations.NotNull()
    io.bloco.biblioteca.database.BookDao bookDao) {
        super();
    }
}