package io.bloco.biblioteca.activities

import androidx.appcompat.app.AppCompatActivity
import io.bloco.biblioteca.App
import io.bloco.biblioteca.common.di.ActivityComponent
import io.bloco.biblioteca.common.di.ActivityModule

abstract class BaseActivity : AppCompatActivity() {

    fun getActivityComponent(): ActivityComponent {
        return (application as App).component.plus(ActivityModule(this))
    }
}