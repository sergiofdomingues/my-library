package io.bloco.biblioteca.common.di

import android.content.Context
import android.content.res.Resources
import com.bumptech.glide.Glide
import dagger.Module
import dagger.Provides
import io.bloco.biblioteca.base.App
import javax.inject.Singleton

@Module
class ApplicationModule(private val app: App) {
    @Provides
    fun app() = app

    @Provides
    fun context() = app as Context

    @Provides
    fun resources(): Resources = app.resources

    @Provides
    fun appMode() = app.mode

    @Provides
    @Singleton
    fun glide() = Glide.with(app)
}