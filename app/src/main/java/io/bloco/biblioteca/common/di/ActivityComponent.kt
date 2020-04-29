package io.bloco.biblioteca.common.di

import dagger.Subcomponent
import io.bloco.biblioteca.bookadd.ui.AddBookActivity
import io.bloco.biblioteca.bookdetails.BookDetailsActivity
import io.bloco.biblioteca.bookmain.ui.MainActivity
import io.bloco.biblioteca.booksearch.SearchBookActivity

@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {
    fun inject(activity: AddBookActivity)
    fun inject(activity: BookDetailsActivity)
    fun inject(activity: MainActivity)
    fun inject(activity: SearchBookActivity)
}