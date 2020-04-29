package io.bloco.biblioteca.common.di

import dagger.Component
import io.bloco.biblioteca.base.App
import io.bloco.biblioteca.database.BookRepository
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, DbModule::class, NetworkModule::class])
interface ApplicationComponent {
    fun inject(app: App)
    fun plus(activityModule: ActivityModule): ActivityComponent

    // For tests
    fun bookRepository(): BookRepository
}