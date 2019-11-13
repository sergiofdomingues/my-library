package io.bloco.biblioteca.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.bloco.biblioteca.app.App
import io.bloco.biblioteca.model.Book
import io.bloco.biblioteca.helpers.Converters

@Database(entities = [Book::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun bookDao(): BookDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return when ((context.applicationContext as App).mode) {
                App.Mode.TEST -> INSTANCE?.let { it }
                    ?: buildDatabaseTest(context).also { INSTANCE = it }
                App.Mode.NORMAL -> INSTANCE?.let { it }
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