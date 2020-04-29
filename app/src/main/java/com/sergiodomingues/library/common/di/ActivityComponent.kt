package com.sergiodomingues.library.common.di

import com.sergiodomingues.library.bookadd.ui.AddBookActivity
import com.sergiodomingues.library.bookdetails.BookDetailsActivity
import com.sergiodomingues.library.bookmain.ui.MainActivity
import com.sergiodomingues.library.booksearch.SearchBookActivity
import dagger.Subcomponent

@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {
    fun inject(activity: AddBookActivity)
    fun inject(activity: BookDetailsActivity)
    fun inject(activity: MainActivity)
    fun inject(activity: SearchBookActivity)
}