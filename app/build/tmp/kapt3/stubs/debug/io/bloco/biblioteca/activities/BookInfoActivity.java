package io.bloco.biblioteca.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import io.bloco.biblioteca.R;
import io.bloco.biblioteca.helpers.ImageLoader;
import io.bloco.biblioteca.model.Book;
import kotlinx.android.synthetic.main.activity_book_info.*;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0012\u0010\r\u001a\u00020\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0014R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lio/bloco/biblioteca/activities/BookInfoActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "imageLoader", "Lio/bloco/biblioteca/helpers/ImageLoader;", "getImageLoader", "()Lio/bloco/biblioteca/helpers/ImageLoader;", "imageLoader$delegate", "Lkotlin/Lazy;", "selectedBook", "Lio/bloco/biblioteca/model/Book;", "initDetailFields", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "Companion", "app_debug"})
public final class BookInfoActivity extends androidx.appcompat.app.AppCompatActivity {
    private io.bloco.biblioteca.model.Book selectedBook;
    private final kotlin.Lazy imageLoader$delegate = null;
    private static final java.lang.String BOOK_KEY = "BOOK";
    public static final io.bloco.biblioteca.activities.BookInfoActivity.Companion Companion = null;
    private java.util.HashMap _$_findViewCache;
    
    private final io.bloco.biblioteca.helpers.ImageLoader getImageLoader() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void initDetailFields(io.bloco.biblioteca.model.Book selectedBook) {
    }
    
    public BookInfoActivity() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lio/bloco/biblioteca/activities/BookInfoActivity$Companion;", "", "()V", "BOOK_KEY", "", "getIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "book", "Lio/bloco/biblioteca/model/Book;", "app_debug"})
    public static final class Companion {
        
        @org.jetbrains.annotations.NotNull()
        public final android.content.Intent getIntent(@org.jetbrains.annotations.NotNull()
        android.content.Context context, @org.jetbrains.annotations.NotNull()
        io.bloco.biblioteca.model.Book book) {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}