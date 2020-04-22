package io.bloco.biblioteca.model;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import io.bloco.biblioteca.R;
import io.bloco.biblioteca.adapter.BooksRecyclerAdapter;
import io.bloco.biblioteca.activities.BookInfoActivity;
import kotlinx.android.synthetic.main.recyclerview_item_row.view.*;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00020\nJ\u0012\u0010\u0010\u001a\u00020\u000f2\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\u0012\u0010\u0011\u001a\u00020\u00122\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\u0018\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\t\u001a\u00020\nH\u0002R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lio/bloco/biblioteca/model/BookHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "Landroid/view/View$OnClickListener;", "Landroid/view/View$OnLongClickListener;", "v", "Landroid/view/View;", "interfaceRef", "Lio/bloco/biblioteca/adapter/BooksRecyclerAdapter$ListItemLongClick;", "(Landroid/view/View;Lio/bloco/biblioteca/adapter/BooksRecyclerAdapter$ListItemLongClick;)V", "book", "Lio/bloco/biblioteca/model/Book;", "dialog", "Landroid/app/AlertDialog;", "view", "bindBook", "", "onClick", "onLongClick", "", "openBookMenu", "context", "Landroid/content/Context;", "app_debug"})
public final class BookHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder implements android.view.View.OnClickListener, android.view.View.OnLongClickListener {
    private android.view.View view;
    private io.bloco.biblioteca.model.Book book;
    private android.app.AlertDialog dialog;
    private final io.bloco.biblioteca.adapter.BooksRecyclerAdapter.ListItemLongClick interfaceRef = null;
    
    @java.lang.Override()
    public void onClick(@org.jetbrains.annotations.Nullable()
    android.view.View v) {
    }
    
    @java.lang.Override()
    public boolean onLongClick(@org.jetbrains.annotations.Nullable()
    android.view.View v) {
        return false;
    }
    
    public final void bindBook(@org.jetbrains.annotations.NotNull()
    io.bloco.biblioteca.model.Book book) {
    }
    
    private final void openBookMenu(android.content.Context context, io.bloco.biblioteca.model.Book book) {
    }
    
    public BookHolder(@org.jetbrains.annotations.NotNull()
    android.view.View v, @org.jetbrains.annotations.NotNull()
    io.bloco.biblioteca.adapter.BooksRecyclerAdapter.ListItemLongClick interfaceRef) {
        super(null);
    }
}