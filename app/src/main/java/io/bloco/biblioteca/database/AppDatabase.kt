package io.bloco.biblioteca.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.bloco.biblioteca.App
import io.bloco.biblioteca.Book
import io.bloco.biblioteca.BookDao

@Database(entities = [Book::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun bookDao(): BookDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return when ((context.applicationContext as App).testing) {
                true -> INSTANCE?.let { it }
                    ?: buildDatabaseTest(context).also { INSTANCE = it }
                false -> INSTANCE?.let { it }
                    ?: buildDatabase(context).also { INSTANCE = it }
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            AppDatabase::class.java, "booksdatabase.db"
        ).build()

        private fun buildDatabaseTest(context: Context) = Room.databaseBuilder(
            context,
            AppDatabase::class.java, "testdatabase.db"
        ).build()

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}