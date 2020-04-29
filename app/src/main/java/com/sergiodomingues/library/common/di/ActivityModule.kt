package com.sergiodomingues.library.common.di

import android.app.Activity
import com.sergiodomingues.library.base.BaseActivity
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: BaseActivity) {

    @Provides
    fun activity(): Activity = activity
}