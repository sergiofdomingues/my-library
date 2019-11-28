package io.bloco.biblioteca.common.di

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import dagger.Module
import dagger.Provides
import io.bloco.biblioteca.App

@Module class ApplicationModule(private val app: App) {

    @Provides
    fun app() = app

    @Provides
    fun context() = app as Context

    @Provides
    fun activity() = app as Activity //Não funciona: Como providenciar uma activity geral
                                     // para o fazer inject no construtor do ImageLoader por exemplo?

    @Provides
    fun resources(): Resources = app.resources
}