package io.bloco.biblioteca.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u0006\u0010\u0005\u001a\u00020\u0004\u00a8\u0006\u0006"}, d2 = {"Lio/bloco/biblioteca/api/HttpClient;", "", "()V", "provideHttpClient", "Lokhttp3/OkHttpClient;", "provideHttpTestClient", "app_debug"})
public final class HttpClient {
    
    @org.jetbrains.annotations.NotNull()
    public final okhttp3.OkHttpClient provideHttpTestClient() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final okhttp3.OkHttpClient provideHttpClient() {
        return null;
    }
    
    public HttpClient() {
        super();
    }
}