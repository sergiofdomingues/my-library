package com.sergiodomingues.library.common.di

import androidx.room.Room
import com.sergiodomingues.library.base.App
import com.sergiodomingues.library.database.AppDatabase
import com.sergiodomingues.library.database.BookDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DbModule {

    @Provides
    @Singleton
    fun provideDatabase(app: App): AppDatabase {
        return if (app.mode == App.Mode.NORMAL)
            Room.databaseBuilder(app, AppDatabase::class.java, "booksdatabase.db")
                .allowMainThreadQueries()
                .build()
        else
            Room.databaseBuilder(app, AppDatabase::class.java, "testdatabase.db")
                .allowMainThreadQueries()
                .build()
    }

    @Provides
    @Singleton
    fun provideBookDao(appDatabase: AppDatabase): BookDao {
        return appDatabase.bookDao()
    }
}