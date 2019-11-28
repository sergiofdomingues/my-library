package io.bloco.biblioteca.common.di

import android.app.Activity
import dagger.Component
import dagger.Subcomponent
import io.bloco.biblioteca.App
import io.bloco.biblioteca.activities.AddBookActivity
import io.bloco.biblioteca.activities.BookInfoActivity
import io.bloco.biblioteca.activities.MainActivity
import io.bloco.biblioteca.activities.SearchBookActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, DbModule::class, NetworkModule::class]) //definir todos os modulos que podem ser injected
interface ApplicationComponent {
    fun inject(app: App)
    fun inject(activity: AddBookActivity)
    fun inject(activity: BookInfoActivity)
    fun inject(activity: MainActivity)
    fun inject(activity: SearchBookActivity)
}