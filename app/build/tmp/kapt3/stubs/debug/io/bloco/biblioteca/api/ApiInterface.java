package io.bloco.biblioteca.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\"\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\u0006H\'J\"\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\t\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\u0006H\'\u00a8\u0006\n"}, d2 = {"Lio/bloco/biblioteca/api/ApiInterface;", "", "getBookByIsbn", "Lretrofit2/Call;", "Lio/bloco/biblioteca/api/BookResponse;", "bookIsbn", "", "apiKey", "getBookByQuery", "query", "app_debug"})
public abstract interface ApiInterface {
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "volumes/{isbn}")
    public abstract retrofit2.Call<io.bloco.biblioteca.api.BookResponse> getBookByIsbn(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Path(value = "isbn")
    java.lang.String bookIsbn, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "API_KEY")
    java.lang.String apiKey);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "volumes")
    public abstract retrofit2.Call<io.bloco.biblioteca.api.BookResponse> getBookByQuery(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "q")
    java.lang.String query, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "API_KEY")
    java.lang.String apiKey);
}