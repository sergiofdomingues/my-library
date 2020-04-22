package io.bloco.biblioteca.api;

import io.bloco.biblioteca.model.FoundBook;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;
import java.lang.Exception;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0004J\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0010\u0010\u0007\u001a\f\u0012\b\u0012\u00060\tR\u00020\n0\bH\u0002J(\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00062\u0018\u0010\u000e\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u0010\u0012\u0004\u0012\u00020\f0\u000fR\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lio/bloco/biblioteca/api/ApiCaller;", "", "apiService", "Lio/bloco/biblioteca/api/ApiInterface;", "(Lio/bloco/biblioteca/api/ApiInterface;)V", "getIsbn", "", "isbnList", "", "Lio/bloco/biblioteca/api/BookResponse$IndustryIdentifier;", "Lio/bloco/biblioteca/api/BookResponse;", "performSearchByQuery", "", "query", "onComplete", "Lkotlin/Function1;", "Lio/bloco/biblioteca/api/BookSearchResult;", "Companion", "app_debug"})
public final class ApiCaller {
    private final io.bloco.biblioteca.api.ApiInterface apiService = null;
    private static final java.lang.String API_KEY = "AIzaSyDV_QELWETLCzg8iHZB7w2_gBwT1VCmnIo";
    private static final java.lang.String ISBN_TYPE_10 = "ISBN_10";
    private static final java.lang.String ISBN_TYPE_13 = "ISBN_13";
    private static final java.lang.String ISBN_TYPE_OTHER = "OTHER";
    private static final int FIRST_3_AUTHORS = 3;
    public static final io.bloco.biblioteca.api.ApiCaller.Companion Companion = null;
    
    public final void performSearchByQuery(@org.jetbrains.annotations.NotNull()
    java.lang.String query, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super io.bloco.biblioteca.api.BookSearchResult<? extends java.lang.Object>, kotlin.Unit> onComplete) {
    }
    
    private final java.lang.String getIsbn(java.util.List<io.bloco.biblioteca.api.BookResponse.IndustryIdentifier> isbnList) {
        return null;
    }
    
    public ApiCaller(@org.jetbrains.annotations.Nullable()
    io.bloco.biblioteca.api.ApiInterface apiService) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Lio/bloco/biblioteca/api/ApiCaller$Companion;", "", "()V", "API_KEY", "", "FIRST_3_AUTHORS", "", "ISBN_TYPE_10", "ISBN_TYPE_13", "ISBN_TYPE_OTHER", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}