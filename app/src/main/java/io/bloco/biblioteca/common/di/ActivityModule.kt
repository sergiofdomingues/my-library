package io.bloco.biblioteca.common.di

import android.app.Activity
import dagger.Module
import dagger.Provides
import io.bloco.biblioteca.activities.BaseActivity

@Module
class ActivityModule(private val activity: BaseActivity) {

    @Provides
    fun activity(): Activity = activity
}