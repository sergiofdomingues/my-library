package io.bloco.biblioteca.base

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.jakewharton.rxrelay2.PublishRelay
import io.bloco.biblioteca.common.di.ActivityComponent
import io.bloco.biblioteca.common.di.ActivityModule

abstract class BaseActivity : AppCompatActivity() {

    fun getActivityComponent(): ActivityComponent {
        return (application as App).component.plus(ActivityModule(this))
    }

    data class ActivityResult(val requestCode: Int, val resultCode: Int, val data: Intent?)
    private val resultsRelay = PublishRelay.create<ActivityResult>()
    fun activityResults() = resultsRelay.hide()

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        resultsRelay.accept(ActivityResult(requestCode, resultCode, data))
    }
}