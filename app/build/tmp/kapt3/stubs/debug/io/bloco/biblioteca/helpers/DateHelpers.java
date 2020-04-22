package io.bloco.biblioteca.helpers;

import android.annotation.SuppressLint;
import timber.log.Timber;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u000b\u001a\u00020\t2\b\u0010\f\u001a\u0004\u0018\u00010\rJ\u000e\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\rJ\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\tH\u0002J\u0010\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\tH\u0002J\u0012\u0010\u0015\u001a\u0004\u0018\u00010\r2\b\b\u0002\u0010\u0012\u001a\u00020\tJ\u0014\u0010\u0016\u001a\u0004\u0018\u00010\r2\b\b\u0002\u0010\u0012\u001a\u00020\tH\u0002J\u0012\u0010\u0017\u001a\u0004\u0018\u00010\r2\b\b\u0002\u0010\u0012\u001a\u00020\tR\u0016\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0010\u0010\u0007\u001a\u00020\u00048\u0002X\u0083\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2 = {"Lio/bloco/biblioteca/helpers/DateHelpers;", "", "()V", "dateFormat", "Ljava/text/SimpleDateFormat;", "getDateFormat", "()Ljava/text/SimpleDateFormat;", "gApiFormat", "googleApiFormat", "", "myFormat", "dateToString", "date", "Ljava/util/Date;", "dateToStringDatePicker", "dateFromPicker", "isInMyFormat", "", "dateString", "isValidYear", "str", "parseStringToDate", "stringFromGApiToDate", "stringToDate", "app_debug"})
public final class DateHelpers {
    private static final java.lang.String myFormat = "dd/MM/yyyy";
    private static final java.lang.String googleApiFormat = "yyyy-MM-dd";
    @org.jetbrains.annotations.NotNull()
    @android.annotation.SuppressLint(value = {"ConstantLocale"})
    private static final java.text.SimpleDateFormat dateFormat = null;
    @android.annotation.SuppressLint(value = {"ConstantLocale"})
    private static final java.text.SimpleDateFormat gApiFormat = null;
    public static final io.bloco.biblioteca.helpers.DateHelpers INSTANCE = null;
    
    @org.jetbrains.annotations.NotNull()
    public final java.text.SimpleDateFormat getDateFormat() {
        return null;
    }
    
    private final java.util.Date stringFromGApiToDate(java.lang.String dateString) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.Date parseStringToDate(@org.jetbrains.annotations.NotNull()
    java.lang.String dateString) {
        return null;
    }
    
    private final boolean isInMyFormat(java.lang.String dateString) {
        return false;
    }
    
    private final boolean isValidYear(java.lang.String str) {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.Date stringToDate(@org.jetbrains.annotations.NotNull()
    java.lang.String dateString) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String dateToString(@org.jetbrains.annotations.Nullable()
    java.util.Date date) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String dateToStringDatePicker(@org.jetbrains.annotations.NotNull()
    java.util.Date dateFromPicker) {
        return null;
    }
    
    private DateHelpers() {
        super();
    }
}