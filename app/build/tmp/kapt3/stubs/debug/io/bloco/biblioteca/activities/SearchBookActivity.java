package io.bloco.biblioteca.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import io.bloco.biblioteca.App;
import io.bloco.biblioteca.R;
import io.bloco.biblioteca.adapter.SearchBooksRecyclerAdapter;
import io.bloco.biblioteca.api.BookSearchResult;
import io.bloco.biblioteca.common.MessageManager;
import io.bloco.biblioteca.model.FoundBook;
import kotlinx.android.synthetic.main.activity_search_book.*;
import timber.log.Timber;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 82\u00020\u00012\u00020\u0002:\u000289B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\u001c\u001a\u00020\u001dH\u0002J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u0011H\u0016J\"\u0010!\u001a\u00020\u001f2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020#2\b\u0010%\u001a\u0004\u0018\u00010\u001dH\u0014J\u0012\u0010&\u001a\u00020\u001f2\b\u0010\'\u001a\u0004\u0018\u00010(H\u0014J\u0010\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,H\u0016J\u0010\u0010-\u001a\u00020*2\u0006\u0010.\u001a\u00020/H\u0016J\u0010\u00100\u001a\u00020\u001f2\u0006\u00101\u001a\u000202H\u0002J\u0016\u00103\u001a\u00020\u001f2\f\u00104\u001a\b\u0012\u0004\u0012\u00020605H\u0002J\b\u00107\u001a\u00020\u001fH\u0002R\u001b\u0010\u0004\u001a\u00020\u00058BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u001b\u0010\n\u001a\u00020\u000b8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000e\u0010\t\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0012\u001a\u00020\u00138BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0016\u0010\t\u001a\u0004\b\u0014\u0010\u0015R\u001b\u0010\u0017\u001a\u00020\u00188BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001b\u0010\t\u001a\u0004\b\u0019\u0010\u001a\u00a8\u0006:"}, d2 = {"Lio/bloco/biblioteca/activities/SearchBookActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "Lio/bloco/biblioteca/adapter/SearchBooksRecyclerAdapter$ListItemClick;", "()V", "adapter", "Lio/bloco/biblioteca/adapter/SearchBooksRecyclerAdapter;", "getAdapter", "()Lio/bloco/biblioteca/adapter/SearchBooksRecyclerAdapter;", "adapter$delegate", "Lkotlin/Lazy;", "api", "Lio/bloco/biblioteca/api/ApiCaller;", "getApi", "()Lio/bloco/biblioteca/api/ApiCaller;", "api$delegate", "foundBooksList", "", "Lio/bloco/biblioteca/model/FoundBook;", "linearLayoutManager", "Landroidx/recyclerview/widget/LinearLayoutManager;", "getLinearLayoutManager", "()Landroidx/recyclerview/widget/LinearLayoutManager;", "linearLayoutManager$delegate", "messageManager", "Lio/bloco/biblioteca/common/MessageManager;", "getMessageManager", "()Lio/bloco/biblioteca/common/MessageManager;", "messageManager$delegate", "getResultIntent", "Landroid/content/Intent;", "itemClick", "", "book", "onActivityResult", "requestCode", "", "resultCode", "data", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "", "menu", "Landroid/view/Menu;", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "performBookSearch", "query", "", "refreshFoundBooksList", "response", "Lio/bloco/biblioteca/api/BookSearchResult;", "", "returnToMainActivity", "Companion", "QueryTextListener", "app_debug"})
public final class SearchBookActivity extends androidx.appcompat.app.AppCompatActivity implements io.bloco.biblioteca.adapter.SearchBooksRecyclerAdapter.ListItemClick {
    private final java.util.List<io.bloco.biblioteca.model.FoundBook> foundBooksList = null;
    private final kotlin.Lazy linearLayoutManager$delegate = null;
    private final kotlin.Lazy adapter$delegate = null;
    private final kotlin.Lazy api$delegate = null;
    private final kotlin.Lazy messageManager$delegate = null;
    private static final int ADD_NEW_BOOK = 10;
    private static final java.lang.String RESULT_NEW_BOOK = "ADDED_BOOK";
    public static final io.bloco.biblioteca.activities.SearchBookActivity.Companion Companion = null;
    private java.util.HashMap _$_findViewCache;
    
    private final androidx.recyclerview.widget.LinearLayoutManager getLinearLayoutManager() {
        return null;
    }
    
    private final io.bloco.biblioteca.adapter.SearchBooksRecyclerAdapter getAdapter() {
        return null;
    }
    
    private final io.bloco.biblioteca.api.ApiCaller getApi() {
        return null;
    }
    
    private final io.bloco.biblioteca.common.MessageManager getMessageManager() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void itemClick(@org.jetbrains.annotations.NotNull()
    io.bloco.biblioteca.model.FoundBook book) {
    }
    
    @java.lang.Override()
    public boolean onCreateOptionsMenu(@org.jetbrains.annotations.NotNull()
    android.view.Menu menu) {
        return false;
    }
    
    @java.lang.Override()
    public boolean onOptionsItemSelected(@org.jetbrains.annotations.NotNull()
    android.view.MenuItem item) {
        return false;
    }
    
    @java.lang.Override()
    protected void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent data) {
    }
    
    private final void performBookSearch(java.lang.String query) {
    }
    
    private final void refreshFoundBooksList(io.bloco.biblioteca.api.BookSearchResult<? extends java.lang.Object> response) {
    }
    
    private final void returnToMainActivity() {
    }
    
    private final android.content.Intent getResultIntent() {
        return null;
    }
    
    public SearchBookActivity() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0006H\u0016\u00a8\u0006\t"}, d2 = {"Lio/bloco/biblioteca/activities/SearchBookActivity$QueryTextListener;", "Landroid/widget/SearchView$OnQueryTextListener;", "(Lio/bloco/biblioteca/activities/SearchBookActivity;)V", "onQueryTextChange", "", "newText", "", "onQueryTextSubmit", "query", "app_debug"})
    final class QueryTextListener implements android.widget.SearchView.OnQueryTextListener {
        
        @java.lang.Override()
        public boolean onQueryTextSubmit(@org.jetbrains.annotations.NotNull()
        java.lang.String query) {
            return false;
        }
        
        @java.lang.Override()
        public boolean onQueryTextChange(@org.jetbrains.annotations.NotNull()
        java.lang.String newText) {
            return false;
        }
        
        public QueryTextListener() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lio/bloco/biblioteca/activities/SearchBookActivity$Companion;", "", "()V", "ADD_NEW_BOOK", "", "RESULT_NEW_BOOK", "", "getIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "app_debug"})
    public static final class Companion {
        
        @org.jetbrains.annotations.NotNull()
        public final android.content.Intent getIntent(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}