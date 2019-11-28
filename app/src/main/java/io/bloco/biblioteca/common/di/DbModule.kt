package io.bloco.biblioteca.common.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import io.bloco.biblioteca.App
import io.bloco.biblioteca.database.AppDatabase
import io.bloco.biblioteca.database.BookDao
import javax.inject.Singleton

@Module
class DbModule {

    @Provides
    @Singleton
    fun provideDatabase(app: App): AppDatabase {
        return Room.databaseBuilder(app, AppDatabase::class.java, "booksdatabase.db")
            .allowMainThreadQueries()
            .build()
    }

/*    @Provides
    @Singleton
    internal fun provideDatabaseTest(app: Application): AppDatabase {
        return Room.databaseBuilder(app, AppDatabase::class.java, "testdatabase.db")
            .allowMainThreadQueries()
            .build()
    }*/

    @Provides
    @Singleton
    fun provideBookDao(appDatabase: AppDatabase): BookDao {
        return appDatabase.bookDao()
    }
}