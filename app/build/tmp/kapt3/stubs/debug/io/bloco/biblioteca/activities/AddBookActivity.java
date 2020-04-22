package io.bloco.biblioteca.activities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import io.bloco.biblioteca.App;
import io.bloco.biblioteca.R;
import io.bloco.biblioteca.helpers.DateHelpers;
import io.bloco.biblioteca.helpers.IntentManager;
import io.bloco.biblioteca.helpers.Validation;
import io.bloco.biblioteca.helpers.ValidationErrors;
import io.bloco.biblioteca.helpers.FileManager;
import io.bloco.biblioteca.helpers.ImageLoader;
import io.bloco.biblioteca.model.Book;
import io.bloco.biblioteca.model.FoundBook;
import kotlinx.android.synthetic.main.activity_add_book.*;
import timber.log.Timber;
import java.io.File;
import java.io.IOException;
import java.util.*;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u00a8\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 P2\u00020\u0001:\u0001PB\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010&\u001a\u00020\'H\u0002J\b\u0010(\u001a\u00020\'H\u0002J\b\u0010)\u001a\u00020*H\u0002J\u0010\u0010+\u001a\u00020\'2\u0006\u0010,\u001a\u00020-H\u0002J\b\u0010.\u001a\u00020\u0011H\u0002J\u0010\u0010/\u001a\u00020\'2\u0006\u00100\u001a\u000201H\u0002J\b\u00102\u001a\u000203H\u0002J\"\u00104\u001a\u00020\'2\u0006\u00105\u001a\u0002062\u0006\u00107\u001a\u0002062\b\u00108\u001a\u0004\u0018\u00010*H\u0014J\u0012\u00109\u001a\u00020\'2\b\u0010:\u001a\u0004\u0018\u00010;H\u0014J\u0012\u0010<\u001a\u00020\n2\b\u0010=\u001a\u0004\u0018\u00010>H\u0016J\b\u0010?\u001a\u00020\'H\u0014J\u0010\u0010@\u001a\u00020\n2\u0006\u0010A\u001a\u00020BH\u0016J\u0010\u0010C\u001a\u00020\'2\u0006\u0010:\u001a\u00020;H\u0014J\u0010\u0010D\u001a\u00020\'2\u0006\u0010E\u001a\u00020;H\u0014J\b\u0010F\u001a\u00020\'H\u0002J\b\u0010G\u001a\u00020\'H\u0002J\u0016\u0010H\u001a\u00020\'2\f\u0010I\u001a\b\u0012\u0004\u0012\u00020K0JH\u0002J\u0010\u0010L\u001a\u00020\'2\u0006\u0010$\u001a\u00020%H\u0002J\u001c\u0010M\u001a\u00020\'2\u0012\u0010N\u001a\u000e\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020\'0OH\u0002R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n \r*\u0004\u0018\u00010\f0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0010\u001a\u00020\u00118BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0014\u0010\b\u001a\u0004\b\u0012\u0010\u0013R\u001b\u0010\u0015\u001a\u00020\u00168BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0019\u0010\b\u001a\u0004\b\u0017\u0010\u0018R\u001b\u0010\u001a\u001a\u00020\u001b8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001e\u0010\b\u001a\u0004\b\u001c\u0010\u001dR\u001b\u0010\u001f\u001a\u00020 8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b#\u0010\b\u001a\u0004\b!\u0010\"R\u0010\u0010$\u001a\u0004\u0018\u00010%X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006Q"}, d2 = {"Lio/bloco/biblioteca/activities/AddBookActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "bookRepository", "Lio/bloco/biblioteca/database/BookRepository;", "getBookRepository", "()Lio/bloco/biblioteca/database/BookRepository;", "bookRepository$delegate", "Lkotlin/Lazy;", "bookSuccessfullyAdded", "", "calendar", "Ljava/util/Calendar;", "kotlin.jvm.PlatformType", "currentPhotoPath", "", "datePicker", "Landroid/app/DatePickerDialog;", "getDatePicker", "()Landroid/app/DatePickerDialog;", "datePicker$delegate", "fileManager", "Lio/bloco/biblioteca/helpers/FileManager;", "getFileManager", "()Lio/bloco/biblioteca/helpers/FileManager;", "fileManager$delegate", "imageLoader", "Lio/bloco/biblioteca/helpers/ImageLoader;", "getImageLoader", "()Lio/bloco/biblioteca/helpers/ImageLoader;", "imageLoader$delegate", "intentManager", "Lio/bloco/biblioteca/helpers/IntentManager;", "getIntentManager", "()Lio/bloco/biblioteca/helpers/IntentManager;", "intentManager$delegate", "tempFile", "Ljava/io/File;", "dispatchTakePictureIntent", "", "dispatchUploadPictureIntent", "getResultIntent", "Landroid/content/Intent;", "initDetailFields", "chosenBook", "Lio/bloco/biblioteca/model/FoundBook;", "initializeDatePicker", "loadPicAsync", "picUri", "Landroid/net/Uri;", "makeBookFromFields", "Lio/bloco/biblioteca/model/Book;", "onActivityResult", "requestCode", "", "resultCode", "data", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "menu", "Landroid/view/Menu;", "onDestroy", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onRestoreInstanceState", "onSaveInstanceState", "outState", "resetLayoutErrors", "returnToSearchActivity", "showLayoutErrors", "errorList", "", "Lio/bloco/biblioteca/helpers/ValidationErrors;", "startTakePicIntent", "takePicAsync", "onComplete", "Lkotlin/Function1;", "Companion", "app_debug"})
public final class AddBookActivity extends androidx.appcompat.app.AppCompatActivity {
    private final kotlin.Lazy fileManager$delegate = null;
    private final kotlin.Lazy imageLoader$delegate = null;
    private final kotlin.Lazy datePicker$delegate = null;
    private final kotlin.Lazy bookRepository$delegate = null;
    private final kotlin.Lazy intentManager$delegate = null;
    private final java.util.Calendar calendar = null;
    private boolean bookSuccessfullyAdded;
    private java.lang.String currentPhotoPath;
    private java.io.File tempFile;
    private static final int ADD_NEW_BOOK = 10;
    private static final java.lang.String RESULT_NEW_BOOK = "ADDED_BOOK";
    private static final java.lang.String CHOSEN_BOOK = "ADDING_BOOK";
    private static final int REQUEST_TAKE_PHOTO = 1;
    private static final int REQUEST_SELECT_PHOTO = 2;
    public static final io.bloco.biblioteca.activities.AddBookActivity.Companion Companion = null;
    private java.util.HashMap _$_findViewCache;
    
    private final io.bloco.biblioteca.helpers.FileManager getFileManager() {
        return null;
    }
    
    private final io.bloco.biblioteca.helpers.ImageLoader getImageLoader() {
        return null;
    }
    
    private final android.app.DatePickerDialog getDatePicker() {
        return null;
    }
    
    private final io.bloco.biblioteca.database.BookRepository getBookRepository() {
        return null;
    }
    
    private final io.bloco.biblioteca.helpers.IntentManager getIntentManager() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
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
    
    @java.lang.Override()
    public boolean onCreateOptionsMenu(@org.jetbrains.annotations.Nullable()
    android.view.Menu menu) {
        return false;
    }
    
    @java.lang.Override()
    protected void onSaveInstanceState(@org.jetbrains.annotations.NotNull()
    android.os.Bundle outState) {
    }
    
    @java.lang.Override()
    protected void onRestoreInstanceState(@org.jetbrains.annotations.NotNull()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
    
    private final void dispatchTakePictureIntent() {
    }
    
    private final void dispatchUploadPictureIntent() {
    }
    
    private final void takePicAsync(kotlin.jvm.functions.Function1<? super java.io.File, kotlin.Unit> onComplete) {
    }
    
    private final void loadPicAsync(android.net.Uri picUri) {
    }
    
    private final void startTakePicIntent(java.io.File tempFile) {
    }
    
    private final void showLayoutErrors(java.util.List<? extends io.bloco.biblioteca.helpers.ValidationErrors> errorList) {
    }
    
    private final void initDetailFields(io.bloco.biblioteca.model.FoundBook chosenBook) {
    }
    
    private final android.app.DatePickerDialog initializeDatePicker() {
        return null;
    }
    
    private final io.bloco.biblioteca.model.Book makeBookFromFields() {
        return null;
    }
    
    private final void resetLayoutErrors() {
    }
    
    private final void returnToSearchActivity() {
    }
    
    private final android.content.Intent getResultIntent() {
        return null;
    }
    
    public AddBookActivity() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rJ\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lio/bloco/biblioteca/activities/AddBookActivity$Companion;", "", "()V", "ADD_NEW_BOOK", "", "CHOSEN_BOOK", "", "REQUEST_SELECT_PHOTO", "REQUEST_TAKE_PHOTO", "RESULT_NEW_BOOK", "getIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "book", "Lio/bloco/biblioteca/model/FoundBook;", "app_debug"})
    public static final class Companion {
        
        @org.jetbrains.annotations.NotNull()
        public final android.content.Intent getIntent(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.content.Intent getIntent(@org.jetbrains.annotations.NotNull()
        android.content.Context context, @org.jetbrains.annotations.NotNull()
        io.bloco.biblioteca.model.FoundBook book) {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}