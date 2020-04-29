package com.sergiodomingues.library.common.di

import com.sergiodomingues.library.base.App
import com.sergiodomingues.library.database.BookRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, DbModule::class, NetworkModule::class])
interface ApplicationComponent {
    fun inject(app: App)
    fun plus(activityModule: ActivityModule): ActivityComponent

    // For tests
    fun bookRepository(): BookRepository
}