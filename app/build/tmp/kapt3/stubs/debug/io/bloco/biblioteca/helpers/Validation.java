package io.bloco.biblioteca.helpers;

import io.bloco.biblioteca.model.Book;
import java.util.*;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0002J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tH\u0002J\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\r\u001a\u00020\u000e\u00a8\u0006\u000f"}, d2 = {"Lio/bloco/biblioteca/helpers/Validation;", "", "()V", "bookDateIsNotValid", "", "bookDate", "Ljava/util/Date;", "bookTitleIsNotFilled", "bookTitle", "", "validateBook", "", "Lio/bloco/biblioteca/helpers/ValidationErrors;", "book", "Lio/bloco/biblioteca/model/Book;", "app_debug"})
public final class Validation {
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<io.bloco.biblioteca.helpers.ValidationErrors> validateBook(@org.jetbrains.annotations.NotNull()
    io.bloco.biblioteca.model.Book book) {
        return null;
    }
    
    private final boolean bookTitleIsNotFilled(java.lang.String bookTitle) {
        return false;
    }
    
    private final boolean bookDateIsNotValid(java.util.Date bookDate) {
        return false;
    }
    
    public Validation() {
        super();
    }
}