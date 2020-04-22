package io.bloco.biblioteca;

import android.app.Application;
import android.os.StrictMode;
import io.bloco.biblioteca.api.ApiCaller;
import io.bloco.biblioteca.api.ApiInterface;
import io.bloco.biblioteca.api.RetrofitInstance;
import io.bloco.biblioteca.database.AppDatabase;
import io.bloco.biblioteca.database.BookRepository;

@kotlin.Suppress(names = {"unused"})
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\u001bB\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0014\u001a\u00020\u0015H\u0002J\u0006\u0010\u0016\u001a\u00020\u0004J\u0006\u0010\u0017\u001a\u00020\u0018J\b\u0010\u0019\u001a\u00020\u0015H\u0016J\b\u0010\u001a\u001a\u00020\u0015H\u0002R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\r\u0010\b\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\u000e\u001a\u00020\u000fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013\u00a8\u0006\u001c"}, d2 = {"Lio/bloco/biblioteca/App;", "Landroid/app/Application;", "()V", "api", "Lio/bloco/biblioteca/api/ApiCaller;", "getApi", "()Lio/bloco/biblioteca/api/ApiCaller;", "api$delegate", "Lkotlin/Lazy;", "db", "Lio/bloco/biblioteca/database/AppDatabase;", "getDb", "()Lio/bloco/biblioteca/database/AppDatabase;", "db$delegate", "mode", "Lio/bloco/biblioteca/App$Mode;", "getMode", "()Lio/bloco/biblioteca/App$Mode;", "setMode", "(Lio/bloco/biblioteca/App$Mode;)V", "checkTestMode", "", "getApiCaller", "getBookRepository", "Lio/bloco/biblioteca/database/BookRepository;", "onCreate", "strictMode", "Mode", "app_debug"})
public final class App extends android.app.Application {
    private final kotlin.Lazy db$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private io.bloco.biblioteca.App.Mode mode;
    private final kotlin.Lazy api$delegate = null;
    
    private final io.bloco.biblioteca.database.AppDatabase getDb() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final io.bloco.biblioteca.App.Mode getMode() {
        return null;
    }
    
    public final void setMode(@org.jetbrains.annotations.NotNull()
    io.bloco.biblioteca.App.Mode p0) {
    }
    
    private final io.bloco.biblioteca.api.ApiCaller getApi() {
        return null;
    }
    
    @java.lang.Override()
    public void onCreate() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final io.bloco.biblioteca.api.ApiCaller getApiCaller() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final io.bloco.biblioteca.database.BookRepository getBookRepository() {
        return null;
    }
    
    private final void strictMode() {
    }
    
    private final void checkTestMode() {
    }
    
    public App() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004\u00a8\u0006\u0005"}, d2 = {"Lio/bloco/biblioteca/App$Mode;", "", "(Ljava/lang/String;I)V", "NORMAL", "TEST", "app_debug"})
    public static enum Mode {
        /*public static final*/ NORMAL /* = new NORMAL() */,
        /*public static final*/ TEST /* = new TEST() */;
        
        Mode() {
        }
    }
}