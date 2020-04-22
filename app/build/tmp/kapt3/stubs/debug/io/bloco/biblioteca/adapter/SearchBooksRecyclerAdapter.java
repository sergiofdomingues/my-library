package io.bloco.biblioteca.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.LayoutRes;
import androidx.recyclerview.widget.RecyclerView;
import io.bloco.biblioteca.R;
import io.bloco.biblioteca.model.FoundBook;
import kotlinx.android.synthetic.main.recyclerview_item_row.view.*;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0002\u001a\u001bB\u001b\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\b\u0010\u000b\u001a\u00020\fH\u0016J\u001c\u0010\r\u001a\u00020\u000e2\n\u0010\u000f\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0010\u001a\u00020\fH\u0016J\u001c\u0010\u0011\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\fH\u0016J \u0010\u0015\u001a\u00020\u0016*\u00020\u00132\b\b\u0001\u0010\u0017\u001a\u00020\f2\b\b\u0002\u0010\u0018\u001a\u00020\u0019H\u0002R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u001c"}, d2 = {"Lio/bloco/biblioteca/adapter/SearchBooksRecyclerAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lio/bloco/biblioteca/adapter/SearchBooksRecyclerAdapter$BookHolder;", "books", "", "Lio/bloco/biblioteca/model/FoundBook;", "interfaceRef", "Lio/bloco/biblioteca/adapter/SearchBooksRecyclerAdapter$ListItemClick;", "(Ljava/util/List;Lio/bloco/biblioteca/adapter/SearchBooksRecyclerAdapter$ListItemClick;)V", "getInterfaceRef", "()Lio/bloco/biblioteca/adapter/SearchBooksRecyclerAdapter$ListItemClick;", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "inflate", "Landroid/view/View;", "layoutRes", "attachToRoot", "", "BookHolder", "ListItemClick", "app_debug"})
public final class SearchBooksRecyclerAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<io.bloco.biblioteca.adapter.SearchBooksRecyclerAdapter.BookHolder> {
    private final java.util.List<io.bloco.biblioteca.model.FoundBook> books = null;
    @org.jetbrains.annotations.NotNull()
    private final io.bloco.biblioteca.adapter.SearchBooksRecyclerAdapter.ListItemClick interfaceRef = null;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public io.bloco.biblioteca.adapter.SearchBooksRecyclerAdapter.BookHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    io.bloco.biblioteca.adapter.SearchBooksRecyclerAdapter.BookHolder holder, int position) {
    }
    
    private final android.view.View inflate(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup $this$inflate, @androidx.annotation.LayoutRes()
    int layoutRes, boolean attachToRoot) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final io.bloco.biblioteca.adapter.SearchBooksRecyclerAdapter.ListItemClick getInterfaceRef() {
        return null;
    }
    
    public SearchBooksRecyclerAdapter(@org.jetbrains.annotations.NotNull()
    java.util.List<io.bloco.biblioteca.model.FoundBook> books, @org.jetbrains.annotations.NotNull()
    io.bloco.biblioteca.adapter.SearchBooksRecyclerAdapter.ListItemClick interfaceRef) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&\u00a8\u0006\u0006"}, d2 = {"Lio/bloco/biblioteca/adapter/SearchBooksRecyclerAdapter$ListItemClick;", "", "itemClick", "", "book", "Lio/bloco/biblioteca/model/FoundBook;", "app_debug"})
    public static abstract interface ListItemClick {
        
        public abstract void itemClick(@org.jetbrains.annotations.NotNull()
        io.bloco.biblioteca.model.FoundBook book);
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u0006\u001a\u00020\u0007J\u0012\u0010\u000b\u001a\u00020\n2\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0016J\u0010\u0010\f\u001a\u00020\n2\u0006\u0010\u0006\u001a\u00020\u0007H\u0002R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lio/bloco/biblioteca/adapter/SearchBooksRecyclerAdapter$BookHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "Landroid/view/View$OnClickListener;", "v", "Landroid/view/View;", "(Lio/bloco/biblioteca/adapter/SearchBooksRecyclerAdapter;Landroid/view/View;)V", "book", "Lio/bloco/biblioteca/model/FoundBook;", "view", "bindBook", "", "onClick", "openSelectedBook", "app_debug"})
    public final class BookHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder implements android.view.View.OnClickListener {
        private android.view.View view;
        private io.bloco.biblioteca.model.FoundBook book;
        
        @java.lang.Override()
        public void onClick(@org.jetbrains.annotations.Nullable()
        android.view.View v) {
        }
        
        public final void bindBook(@org.jetbrains.annotations.NotNull()
        io.bloco.biblioteca.model.FoundBook book) {
        }
        
        private final void openSelectedBook(io.bloco.biblioteca.model.FoundBook book) {
        }
        
        public BookHolder(@org.jetbrains.annotations.NotNull()
        android.view.View v) {
            super(null);
        }
    }
}