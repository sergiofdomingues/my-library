package com.sergiodomingues.library.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sergiodomingues.library.database.AppDatabase.Companion.DB_VERSION
import com.sergiodomingues.library.helpers.Converters
import com.sergiodomingues.library.model.Book

@Database(entities = [Book::class], version = DB_VERSION)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun bookDao(): BookDao

    companion object {
        const val DB_VERSION = 1
    }
}