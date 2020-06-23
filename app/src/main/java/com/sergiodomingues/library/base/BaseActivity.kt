package com.sergiodomingues.library.base

import android.content.Intent
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar
import com.jakewharton.rxrelay2.PublishRelay
import com.sergiodomingues.library.R
import com.sergiodomingues.library.common.di.ActivityComponent
import com.sergiodomingues.library.common.di.ActivityModule

abstract class BaseActivity : AppCompatActivity() {
    data class ActivityResult(val requestCode: Int, val resultCode: Int, val data: Intent?)
    private val resultsRelay = PublishRelay.create<ActivityResult>()
    protected val toolbarOpt by lazy { findViewById<MaterialToolbar?>(R.id.toolbar) }
    fun activityResults() = resultsRelay.hide()

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        resultsRelay.accept(
            ActivityResult(
                requestCode,
                resultCode,
                data
            )
        )
    }

    fun getActivityComponent(): ActivityComponent {
        return (application as App).component.plus(
            ActivityModule(this)
        )
    }

    protected fun setNavigation(@DrawableRes icon: Int, clickListener: (() -> Unit) = { finish() }) {
        toolbarOpt?.setNavigationIcon(icon)
        toolbarOpt?.setNavigationOnClickListener { clickListener.invoke() }
    }
}