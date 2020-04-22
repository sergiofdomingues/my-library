package io.bloco.biblioteca.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.LayoutRes;
import androidx.recyclerview.widget.RecyclerView;
import io.bloco.biblioteca.R;
import io.bloco.biblioteca.model.Book;
import io.bloco.biblioteca.model.BookHolder;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0018B\u001b\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\b\u0010\t\u001a\u00020\nH\u0016J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\nH\u0016J\u0018\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\nH\u0016J \u0010\u0013\u001a\u00020\u0014*\u00020\u00112\b\b\u0001\u0010\u0015\u001a\u00020\n2\b\b\u0002\u0010\u0016\u001a\u00020\u0017H\u0002R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0019"}, d2 = {"Lio/bloco/biblioteca/adapter/BooksRecyclerAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lio/bloco/biblioteca/model/BookHolder;", "books", "", "Lio/bloco/biblioteca/model/Book;", "interfaceRef", "Lio/bloco/biblioteca/adapter/BooksRecyclerAdapter$ListItemLongClick;", "(Ljava/util/List;Lio/bloco/biblioteca/adapter/BooksRecyclerAdapter$ListItemLongClick;)V", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "inflate", "Landroid/view/View;", "layoutRes", "attachToRoot", "", "ListItemLongClick", "app_debug"})
public final class BooksRecyclerAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<io.bloco.biblioteca.model.BookHolder> {
    private final java.util.List<io.bloco.biblioteca.model.Book> books = null;
    private final io.bloco.biblioteca.adapter.BooksRecyclerAdapter.ListItemLongClick interfaceRef = null;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public io.bloco.biblioteca.model.BookHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    io.bloco.biblioteca.model.BookHolder holder, int position) {
    }
    
    private final android.view.View inflate(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup $this$inflate, @androidx.annotation.LayoutRes()
    int layoutRes, boolean attachToRoot) {
        return null;
    }
    
    public BooksRecyclerAdapter(@org.jetbrains.annotations.NotNull()
    java.util.List<io.bloco.biblioteca.model.Book> books, @org.jetbrains.annotations.NotNull()
    io.bloco.biblioteca.adapter.BooksRecyclerAdapter.ListItemLongClick interfaceRef) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&\u00a8\u0006\u0006"}, d2 = {"Lio/bloco/biblioteca/adapter/BooksRecyclerAdapter$ListItemLongClick;", "", "itemDelete", "", "book", "Lio/bloco/biblioteca/model/Book;", "app_debug"})
    public static abstract interface ListItemLongClick {
        
        public abstract void itemDelete(@org.jetbrains.annotations.NotNull()
        io.bloco.biblioteca.model.Book book);
    }
}