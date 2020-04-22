package io.bloco.biblioteca.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import io.bloco.biblioteca.App;
import io.bloco.biblioteca.R;
import io.bloco.biblioteca.adapter.BooksRecyclerAdapter;
import io.bloco.biblioteca.helpers.DividerItemDecoration;
import io.bloco.biblioteca.helpers.FileManager;
import io.bloco.biblioteca.model.Book;
import kotlinx.android.synthetic.main.activity_main.*;
import timber.log.Timber;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\u0018\u0000 \'2\u00020\u00012\u00020\u0002:\u0001\'B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\u0017\u001a\u00020\u0018H\u0002J\u0010\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u0011H\u0016J\"\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001d2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0014J\u0012\u0010!\u001a\u00020\u00182\b\u0010\"\u001a\u0004\u0018\u00010#H\u0014J\u0016\u0010$\u001a\u00020\u00182\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00110&H\u0002R\u001b\u0010\u0004\u001a\u00020\u00058BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u001b\u0010\n\u001a\u00020\u000b8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000e\u0010\t\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0012\u001a\u00020\u00138BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0016\u0010\t\u001a\u0004\b\u0014\u0010\u0015\u00a8\u0006("}, d2 = {"Lio/bloco/biblioteca/activities/MainActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "Lio/bloco/biblioteca/adapter/BooksRecyclerAdapter$ListItemLongClick;", "()V", "adapter", "Lio/bloco/biblioteca/adapter/BooksRecyclerAdapter;", "getAdapter", "()Lio/bloco/biblioteca/adapter/BooksRecyclerAdapter;", "adapter$delegate", "Lkotlin/Lazy;", "bookRepository", "Lio/bloco/biblioteca/database/BookRepository;", "getBookRepository", "()Lio/bloco/biblioteca/database/BookRepository;", "bookRepository$delegate", "booksList", "", "Lio/bloco/biblioteca/model/Book;", "linearLayoutManager", "Landroidx/recyclerview/widget/LinearLayoutManager;", "getLinearLayoutManager", "()Landroidx/recyclerview/widget/LinearLayoutManager;", "linearLayoutManager$delegate", "getAllBooks", "", "itemDelete", "book", "onActivityResult", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "refreshList", "books", "", "Companion", "app_debug"})
public final class MainActivity extends androidx.appcompat.app.AppCompatActivity implements io.bloco.biblioteca.adapter.BooksRecyclerAdapter.ListItemLongClick {
    private final java.util.List<io.bloco.biblioteca.model.Book> booksList = null;
    private final kotlin.Lazy linearLayoutManager$delegate = null;
    private final kotlin.Lazy adapter$delegate = null;
    private final kotlin.Lazy bookRepository$delegate = null;
    private static final int ADD_NEW_BOOK = 10;
    public static final io.bloco.biblioteca.activities.MainActivity.Companion Companion = null;
    private java.util.HashMap _$_findViewCache;
    
    private final androidx.recyclerview.widget.LinearLayoutManager getLinearLayoutManager() {
        return null;
    }
    
    private final io.bloco.biblioteca.adapter.BooksRecyclerAdapter getAdapter() {
        return null;
    }
    
    private final io.bloco.biblioteca.database.BookRepository getBookRepository() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    protected void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent data) {
    }
    
    @java.lang.Override()
    public void itemDelete(@org.jetbrains.annotations.NotNull()
    io.bloco.biblioteca.model.Book book) {
    }
    
    private final void getAllBooks() {
    }
    
    private final void refreshList(java.util.List<io.bloco.biblioteca.model.Book> books) {
    }
    
    public MainActivity() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lio/bloco/biblioteca/activities/MainActivity$Companion;", "", "()V", "ADD_NEW_BOOK", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}