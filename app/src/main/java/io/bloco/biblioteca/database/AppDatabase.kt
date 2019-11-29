package io.bloco.biblioteca.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.bloco.biblioteca.helpers.Converters
import io.bloco.biblioteca.model.Book

@Database(entities = [Book::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {

    abstract fun bookDao(): BookDao
}