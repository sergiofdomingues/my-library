package io.bloco.biblioteca.helpers;

import android.content.Context;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import androidx.core.util.Preconditions;
import timber.log.Timber;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010\u000b\u001a\u00020\bJ\u0010\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\nJ\u0010\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lio/bloco/biblioteca/helpers/FileManager;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "copyPhotoFileToAppStorage", "Ljava/io/File;", "photoUri", "Landroid/net/Uri;", "createImageFile", "deletePhotoFile", "", "bookPhotoPath", "", "app_debug"})
public final class FileManager {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    
    @org.jetbrains.annotations.NotNull()
    public final java.io.File createImageFile() throws java.io.IOException {
        return null;
    }
    
    public final void deletePhotoFile(@org.jetbrains.annotations.Nullable()
    java.lang.String bookPhotoPath) {
    }
    
    public final void deletePhotoFile(@org.jetbrains.annotations.Nullable()
    android.net.Uri bookPhotoPath) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.io.File copyPhotoFileToAppStorage(@org.jetbrains.annotations.NotNull()
    android.net.Uri photoUri) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.content.Context getContext() {
        return null;
    }
    
    public FileManager(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
}