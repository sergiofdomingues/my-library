package io.bloco.biblioteca.common.di

import dagger.Component
import io.bloco.biblioteca.App
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, DbModule::class, NetworkModule::class]) //definir todos os modulos que podem ser injected
interface ApplicationComponent {
    fun inject(app: App)
    fun plus(activityModule: ActivityModule): ActivityComponent
}