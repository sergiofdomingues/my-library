package io.bloco.biblioteca.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import io.bloco.biblioteca.App;
import io.bloco.biblioteca.helpers.Converters;
import io.bloco.biblioteca.model.Book;

@androidx.room.TypeConverters(value = {io.bloco.biblioteca.helpers.Converters.class})
@androidx.room.Database(entities = {io.bloco.biblioteca.model.Book.class}, version = 1)
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \u00052\u00020\u0001:\u0001\u0005B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&\u00a8\u0006\u0006"}, d2 = {"Lio/bloco/biblioteca/database/AppDatabase;", "Landroidx/room/RoomDatabase;", "()V", "bookDao", "Lio/bloco/biblioteca/database/BookDao;", "Companion", "app_debug"})
public abstract class AppDatabase extends androidx.room.RoomDatabase {
    private static io.bloco.biblioteca.database.AppDatabase INSTANCE;
    public static final io.bloco.biblioteca.database.AppDatabase.Companion Companion = null;
    
    @org.jetbrains.annotations.NotNull()
    public abstract io.bloco.biblioteca.database.BookDao bookDao();
    
    public AppDatabase() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\u000e\u0010\t\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Lio/bloco/biblioteca/database/AppDatabase$Companion;", "", "()V", "INSTANCE", "Lio/bloco/biblioteca/database/AppDatabase;", "buildDatabase", "context", "Landroid/content/Context;", "buildDatabaseTest", "getDatabase", "app_debug"})
    public static final class Companion {
        
        @org.jetbrains.annotations.NotNull()
        public final io.bloco.biblioteca.database.AppDatabase getDatabase(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
        
        private final io.bloco.biblioteca.database.AppDatabase buildDatabase(android.content.Context context) {
            return null;
        }
        
        private final io.bloco.biblioteca.database.AppDatabase buildDatabaseTest(android.content.Context context) {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}