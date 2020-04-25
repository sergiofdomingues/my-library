package io.bloco.biblioteca.common.di

import dagger.Subcomponent
import io.bloco.biblioteca.activities.AddBookActivity
import io.bloco.biblioteca.activities.BookInfoActivity
import io.bloco.biblioteca.activities.MainActivity
import io.bloco.biblioteca.activities.SearchBookActivity

@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {
    fun inject(activity: AddBookActivity)
    fun inject(activity: BookInfoActivity)
    fun inject(activity: MainActivity)
    fun inject(activity: SearchBookActivity)
}